package com.kotlin.pagingdbnet.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.blankj.utilcode.util.LogUtils
import com.kotlin.pagingdbnet.api.NetworkService
import com.kotlin.pagingdbnet.api.RankAppListApi
import com.kotlin.pagingdbnet.db.AppListDataBase
import com.kotlin.pagingdbnet.model.AppData

/**
 *    desc   :
 *    date   : 2022/5/25 21:10
 */
@OptIn(ExperimentalPagingApi::class)
class AppListRemoteMediator(
    private val database: AppListDataBase,
    private val rankAppListApi: RankAppListApi
) : RemoteMediator<Int, AppData>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AppData>
    ): MediatorResult {
        val appListDao = database.getAppListDao()
        return try {
            Log.d("AppListRemoteMediator", "loadType===${loadType}==loadKey==")
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    Log.d("AppListRemoteMediator", "lastItem===${lastItem}")
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    if (lastItem.page == 0) {
                        lastItem.page + 2
                    } else {
                        lastItem.page + 1
                    }
                }
            }
            val pageSize = loadKey ?: 0

            Log.d("AppListRemoteMediator", "loadType===${loadType}==pageSize==$pageSize")

            val response = rankAppListApi.getAppList(page = pageSize)

            Log.d("AppListRemoteMediator", "response===${response}")

            if (response.data != null && response.data.isNotEmpty()) {
                database.withTransaction {
                    for (data in response.data) {
                        data.page = pageSize
                    }
                    appListDao.insertAll(response.data)
                }
            }
            MediatorResult.Success(
                endOfPaginationReached = response.data.isNullOrEmpty()
            )
        } catch (e: Exception) {

            MediatorResult.Error(e)
        }
    }


}
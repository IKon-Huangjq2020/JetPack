package com.kotlin.pagingnet.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kotlin.pagingnet.api.NetworkService
import com.kotlin.pagingnet.model.AppData
import java.lang.IllegalStateException

/**
 *    desc   :
 *    date   : 2022/5/21 16:00
 */
class NetworkPagingSource : PagingSource<Int, AppData>() {

    override fun getRefreshKey(state: PagingState<Int, AppData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AppData> {
        //当前页
        val currentPage = params.key ?: 1
        //上一页
        val prevKey: Int? = if (currentPage == 1) null else currentPage - 1
        //下一页
        val nextKey: Int? = if (currentPage < 50) currentPage + 1 else null

        return try {
            //从网络获取数据
            val result = NetworkService.rankAppListApi.getAppList(currentPage)
            if (result?.data != null) {
                LoadResult.Page(
                    data = result.data,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(IllegalStateException("数据为空"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
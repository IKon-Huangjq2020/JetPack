package com.kotlin.pagingdbnet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.kotlin.pagingdbnet.api.NetworkService
import com.kotlin.pagingdbnet.db.AppListDataBase
import com.kotlin.pagingdbnet.model.AppData
import com.kotlin.pagingdbnet.paging.AppListRemoteMediator
import kotlinx.coroutines.flow.Flow

/**
 *    desc   :
 *    date   : 2022/5/21 14:00
 */
class MainViewModel : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    fun loadNews(): Flow<PagingData<AppData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            remoteMediator = AppListRemoteMediator(
                AppListDataBase.getDB(),
                NetworkService.appListApi
            )
        ) {
            AppListDataBase.getDB().getAppListDao().pagingSource()
        }.flow.cachedIn(viewModelScope)
    }
}
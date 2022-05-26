package com.kotlin.pagingnet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kotlin.pagingnet.model.AppData
import com.kotlin.pagingnet.paging.NetworkPagingSource
import kotlinx.coroutines.flow.Flow

/**
 *    desc   :
 *    date   : 2022/5/21 14:00
 */
class MainViewModel : ViewModel() {

    fun loadNews(): Flow<PagingData<AppData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { NetworkPagingSource() }
        ).flow.cachedIn(viewModelScope)
    }
}
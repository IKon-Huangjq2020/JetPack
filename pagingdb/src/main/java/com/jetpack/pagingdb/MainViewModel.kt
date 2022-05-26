package com.jetpack.pagingdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jetpack.pagingdb.db.UserDataBase

/**
 *    desc   :
 *    date   : 2022/5/23 21:32
 */
class MainViewModel : ViewModel() {
    private val userDao = UserDataBase.getDB().getUserDao()


    val allUsers = Pager(
        PagingConfig(
            pageSize = 50,
            enablePlaceholders = true,
            maxSize = 200
        )
    ) {
        userDao.allUserById()
    }.flow.cachedIn(viewModelScope)

}
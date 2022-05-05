package com.hjq.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjq.room.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn

/**
 *    desc   :
 *    date   : 2022/5/5 13:47
 */
class MainViewModel : ViewModel() {

    private val mUserRepository = UserRepository()


    fun insertUsers(vararg users: User) {
        flow {
            mUserRepository.insertUsers(*users)
            emit(true)
        }.flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun deleteUser(user: User) {
        flow {
            mUserRepository.deleteUser(user)
            emit(true)
        }.flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun updateUser(user: User) {
        flow {
            mUserRepository.updateUser(user)
            emit(true)
        }.flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }


    fun getAllUser() = flow {
        emit(mUserRepository.getAllUsers())
    }.flowOn(Dispatchers.IO)

    fun getUserFlow() = mUserRepository.getAllUserFlow()


    fun getUserLiveData() = mUserRepository.getAllUserLiveData()


}
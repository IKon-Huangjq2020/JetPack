package com.hjq.room

import com.hjq.room.db.UserDataBase
import com.hjq.room.model.User

/**
 *    desc   :
 *    date   : 2022/5/5 13:58
 */
class UserRepository {


    fun insertUsers(vararg users: User) {
        UserDataBase.getDB().getUserDao().insertUsers(*users)
    }

    fun deleteUser(user: User) {
        UserDataBase.getDB().getUserDao().deleteUser(user)
    }

    fun updateUser(user: User) {
        UserDataBase.getDB().getUserDao().updateUser(user)
    }


    fun getAllUsers(): List<User> {
        return UserDataBase.getDB().getUserDao().getAllUser()
    }

    fun getAllUserFlow() = UserDataBase.getDB().getUserDao().getAllUserFlow()

    fun getAllUserLiveData() = UserDataBase.getDB().getUserDao().getAllUserLiveData()
}
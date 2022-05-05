package com.hjq.room.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hjq.room.model.User
import kotlinx.coroutines.flow.Flow

/**
 *    desc   :
 *    date   : 2022/5/5 11:46
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)


    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>


    @Query("SELECT * FROM user")
    fun getAllUserFlow(): Flow<List<User>>


    @Query("SELECT * FROM user")
    fun getAllUserLiveData(): LiveData<List<User>?>


}
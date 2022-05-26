package com.jetpack.pagingdb.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.jetpack.pagingdb.entity.User

/**
 *    desc   :
 *    date   : 2022/5/5 11:46
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun insertUsers(users: List<User>)


    @Query("SELECT * FROM user ORDER BY id COLLATE NOCASE ASC")
    fun allUserById(): PagingSource<Int, User>

}
package com.kotlin.pagingdbnet.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.pagingdbnet.model.AppData

/**
 *    desc   :
 *    date   : 2022/5/25 21:04
 */
@Dao
interface AppListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<AppData>)

    @Query("SELECT * FROM app_list")
    fun pagingSource(): PagingSource<Int, AppData>

    @Query("DELETE FROM app_list")
    suspend fun clearAll()
}
package com.kotlin.pagingdbnet.db

import android.content.Context
import androidx.room.*
import com.kotlin.pagingdbnet.model.AppData

/**
 *    desc   :
 *    date   : 2022/5/5 11:45
 */
@Database(
    entities = [AppData::class],
    version = 1,
    exportSchema = false,
)
abstract class AppListDataBase : RoomDatabase() {


    companion object {

        private var INSTANCE: AppListDataBase? = null

        fun init(context: Context) {
            if (INSTANCE == null) {
                synchronized(AppListDataBase::class.java) {
                    if (INSTANCE == null) {
                        create(context)
                    }
                }
            }
        }

        private fun create(context: Context) {
            INSTANCE =
                Room.databaseBuilder(context, AppListDataBase::class.java, "app_list_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

        }


        fun getDB(): AppListDataBase {
            return INSTANCE ?: throw NullPointerException("UserDataBase 未初始化")
        }


    }


    //增加抽象方法，Room框架会自动实现这个方法
    abstract fun getAppListDao(): AppListDao


}
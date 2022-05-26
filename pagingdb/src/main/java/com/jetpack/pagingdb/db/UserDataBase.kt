package com.jetpack.pagingdb.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jetpack.pagingdb.db.dao.UserDao
import com.jetpack.pagingdb.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 *    desc   :
 *    date   : 2022/5/5 11:45
 */
@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false,
)
abstract class UserDataBase : RoomDatabase() {


    companion object {
        private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        private var INSTANCE: UserDataBase? = null

        fun init(context: Context) {
            if (INSTANCE == null) {
                synchronized(UserDataBase::class.java) {
                    if (INSTANCE == null) {
                        create(context)
                    }
                }
            }
        }

        private fun create(context: Context) {
            INSTANCE =
                Room.databaseBuilder(context, UserDataBase::class.java, "user_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            fillInDb(context.applicationContext)
                        }
                    }).build()

        }


        fun getDB(): UserDataBase {
            return INSTANCE ?: throw NullPointerException("UserDataBase 未初始化")
        }

        private fun fillInDb(context: Context) {
            coroutineScope.launch {
                getDB().getUserDao().insertUsers(getUserList())
            }
        }


        private fun getUserList(): List<User> {
            val userList = mutableListOf<User>()
            for (id in 1..5000) {
                userList.add(User(id, "userName_$id", age = id))
            }
            return userList
        }

    }


    //增加抽象方法，Room框架会自动实现这个方法
    abstract fun getUserDao(): UserDao


}
package com.hjq.room.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.blankj.utilcode.util.Utils
import com.hjq.room.db.dao.UserDao
import com.hjq.room.model.User

/**
 *    desc   :
 *    date   : 2022/5/5 11:45
 */
@Database(
    entities = [User::class],
    version = 5,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 2, to = 3, spec = UserDataBase.MyAutoMigration::class),
        AutoMigration(from = 3, to = 4), AutoMigration(from = 4, to = 5)]
)
abstract class UserDataBase : RoomDatabase() {


    companion object {


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
                    .addMigrations(Migration_1_2)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

        }


        fun getDB(): UserDataBase {
            return INSTANCE ?: throw NullPointerException("UserDataBase 未初始化")
        }


        private val Migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN city TEXT")
            }

        }


    }

    //自动更新
    @RenameColumn(tableName = "user", fromColumnName = "city", toColumnName = "city_name")
    class MyAutoMigration : AutoMigrationSpec


    //增加抽象方法，Room框架会自动实现这个方法
    abstract fun getUserDao(): UserDao


}
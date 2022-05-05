package com.hjq.room

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.hjq.room.db.UserDataBase

/**
 *    desc   :
 *    date   : 2022/5/5 11:29
 */
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        LogUtils.getConfig().setLogHeadSwitch(false).setBorderSwitch(false).isLogSwitch =
            BuildConfig.DEBUG
        UserDataBase.init(this)
    }
}
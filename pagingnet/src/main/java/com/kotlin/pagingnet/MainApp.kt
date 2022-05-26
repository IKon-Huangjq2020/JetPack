package com.kotlin.pagingnet

import android.app.Application
import com.blankj.utilcode.BuildConfig
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils

/**
 *    desc   :
 *    date   : 2022/5/21 13:59
 */
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        LogUtils.getConfig().setLogHeadSwitch(false).setBorderSwitch(false).isLogSwitch =
            BuildConfig.DEBUG
    }
}
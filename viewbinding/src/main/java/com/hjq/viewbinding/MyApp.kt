package com.hjq.viewbinding

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils

/**
 *    desc   :
 *    date   : 2022/5/8 19:50
 */

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        LogUtils.getConfig().setLogHeadSwitch(false).setBorderSwitch(false).isLogSwitch =
            BuildConfig.DEBUG
    }
}
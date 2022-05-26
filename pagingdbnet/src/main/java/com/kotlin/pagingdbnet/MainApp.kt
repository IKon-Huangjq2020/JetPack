package com.kotlin.pagingdbnet

import androidx.multidex.MultiDexApplication
import com.blankj.utilcode.util.Utils
import com.kotlin.pagingdbnet.db.AppListDataBase

/**
 *    desc   :
 *    date   : 2022/5/21 13:59
 */
class MainApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        AppListDataBase.init(this)
    }
}
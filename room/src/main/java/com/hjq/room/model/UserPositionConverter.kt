package com.hjq.room.model

import androidx.room.TypeConverter
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson

/**
 *    desc   :
 *    date   : 2022/5/5 15:20
 */
object UserPositionConverter {

    @TypeConverter
    fun objectToString(value: String?): UserPosition? {
        try {
            return Gson().fromJson(value, UserPosition::class.java)
        } catch (e: Exception) {
            LogUtils.d("e===$e")
            e.printStackTrace()
        }
        return null
    }

    @TypeConverter
    fun stringToObject(deviceEventsBean: UserPosition?): String {
        try {
            return Gson().toJson(deviceEventsBean)
        } catch (e: Exception) {
            LogUtils.d("e===$e")
            e.printStackTrace()
        }
        return ""
    }
}
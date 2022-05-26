package com.kotlin.pagingdbnet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_list")
data class AppData(
    @PrimaryKey
    val apkid: String,
    val down_url: String,
    val download_times: Int,
    val logo_url: String,
    val logo_url_160: String,
    val name: String,
    val needapkdata: String,
    val origin: String,
    val os_version: Int,
    val s_down_url: String,
    val short_word: String,
    val single_word: String,
    val size: String,
    val type: String,
    val version_code: Int,
    val version_name: String,

    ) {
    var page: Int = 1
    override fun toString(): String {
        return "AppData(apkid='$apkid', down_url='$down_url', download_times=$download_times, logo_url='$logo_url', logo_url_160='$logo_url_160', name='$name', needapkdata='$needapkdata', origin='$origin', os_version=$os_version, s_down_url='$s_down_url', short_word='$short_word', single_word='$single_word', size='$size', type='$type', version_code=$version_code, version_name='$version_name', page=$page)"
    }


}
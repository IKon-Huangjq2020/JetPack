package com.hjq.room.model

import androidx.room.*

/**
 *    desc   : 用户
 *    date   : 2022/5/5 11:39
 */
@Entity(tableName = "user")
@TypeConverters(UserPositionConverter::class)
class User {

    @Ignore
    constructor(id: Int) {
        this.id = id
    }


    @Ignore
    constructor(firstName: String?, age: Int) {
        this.firstName = firstName
        this.age = age
    }


    constructor(id: Int, firstName: String?, age: Int) {
        this.id = id
        this.firstName = firstName
        this.age = age
    }


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "first_name", typeAffinity = ColumnInfo.TEXT)
    var firstName: String? = null

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    var age: Int = 0

    //增加城市这一行
    var city: String? = null


    //增加城市这一行
    @ColumnInfo(name = "county", typeAffinity = ColumnInfo.TEXT)
    var county: String? = null


    @ColumnInfo(name = "user_position")
    var userPosition: UserPosition? = null


}
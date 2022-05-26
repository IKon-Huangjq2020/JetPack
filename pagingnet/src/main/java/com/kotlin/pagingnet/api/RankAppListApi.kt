package com.kotlin.pagingnet.api

import com.kotlin.pagingnet.model.RankAppListResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *    desc   :
 *    date   : 2022/5/21 13:52
 */
interface RankAppListApi {

    @GET("app/getRankAppList?s_stream_app=1&cid=1&order=weekpure&tag=视频")
    suspend fun getAppList(
        @Query("page") page: Int
    ): RankAppListResponse?
}
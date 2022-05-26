package com.kotlin.pagingnet.model

data class RankAppListResponse(
    val bg_img: String?,
    val `data`: List<AppData>?,
    val end_state: String,
    val errno: String,
    val tag_conf: List<String>?,
    val total: Int
)
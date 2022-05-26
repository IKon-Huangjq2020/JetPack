package com.kotlin.pagingdbnet.model

data class RankAppListResponse(
    val `data`: List<AppData>?,
    val end_state: String,
    val errno: String,
    val total: Int?
)
package com.rentwiz.app.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EndPoint(
    @Json(name = "end_point") val endPoint: String,
)

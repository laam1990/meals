package com.example.yape.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponseData<T>(
    @Json(name = "meals")
    val meals: List<T>?
)
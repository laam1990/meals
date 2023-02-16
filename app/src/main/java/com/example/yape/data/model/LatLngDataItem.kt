package com.example.yape.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatLngDataItem(
    @Json(name = "latlng")
    val latlng: List<Double?>?
)
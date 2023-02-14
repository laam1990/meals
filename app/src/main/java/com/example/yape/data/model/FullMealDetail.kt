package com.example.yape.data.model


import com.squareup.moshi.Json

data class FullMealDetail(
    @Json(name = "meals")
    val meals: List<MealDetail>?
)
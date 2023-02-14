package com.example.yape.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoundMeals(
    @Json(name = "meals")
    val mealFounded: List<MealDetail?>?
)
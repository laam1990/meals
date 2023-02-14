package com.example.yape.data.api

import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.Meal
import com.example.yape.data.model.MealDetail
import retrofit2.http.Query

interface RemoteDataSource {

    suspend fun getMeals(
        @Query("s") name: String
    ): BaseResponseData<MealDetail>

    suspend fun getMealsFromIngredient(
        @Query("i") ingredient: String
    ): BaseResponseData<Meal>

    suspend fun getDetailMealFromID(
        @Query("i") id: String
    ): BaseResponseData<MealDetail>

}
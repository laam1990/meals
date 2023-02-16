package com.example.yape.data.api.meal

import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.Meal
import com.example.yape.data.model.MealDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
     * Search meal by name
     * http://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
     */

    @GET("v1/1/search.php")
    suspend fun getMeals(
        @Query("s") name: String
    ): BaseResponseData<MealDetail>

    /**
     * Filter by main ingredient
     * www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast
     */
    @GET("v1/1/filter.php")
    suspend fun getMealsFromIngredient(
        @Query("i") ingredient: String
    ): BaseResponseData<Meal>

    /**
     * Lookup full meal details by id
     * www.themealdb.com/api/json/v1/1/lookup.php?i=52772
     */
    //TODO es posible que no haga falta utilizar ese a menos que se busque por ingrediente
    @GET("v1/1/lookup.php")
    suspend fun getDetailMealFromID(
        @Query("i") id: String
    ): BaseResponseData<MealDetail>

}
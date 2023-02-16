package com.example.yape.data.api.meal

import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.Meal
import com.example.yape.data.model.MealDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService): RemoteDataSource {

    override suspend fun getMeals(name: String): BaseResponseData<MealDetail> =
        apiService.getMeals(name)

    override suspend fun getMealsFromIngredient(ingredient: String): BaseResponseData<Meal> =
        apiService.getMealsFromIngredient(ingredient)

    override suspend fun getDetailMealFromID(id: String): BaseResponseData<MealDetail> =
        apiService.getDetailMealFromID(id)
}
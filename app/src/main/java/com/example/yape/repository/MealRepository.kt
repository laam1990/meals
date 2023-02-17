package com.example.yape.repository

import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.Meal
import com.example.yape.data.model.MealDetail
import com.example.yape.data.util.Resource
import com.example.yape.ui.model.MealViewData
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun getMeals(name: String) : Flow<Resource<List<MealViewData>>>

    fun getMealsFromIngredient(ingredient: String): Flow<Resource<List<MealViewData>>>

    fun getDetailMealFromID(id: String): Flow<Resource<BaseResponseData<MealDetail>>>

}
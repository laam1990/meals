package com.example.yape.repository

import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.Meal
import com.example.yape.data.model.MealDetail
import com.example.yape.ui.model.MealViewData

interface MealMapperFacade {
    fun getMealExecuteMapper(
        baseResponseData: BaseResponseData<MealDetail>) : List<MealViewData>
    fun getMealFromIngredientExecuteMapper(
        baseResponseData: BaseResponseData<Meal>) : List<MealViewData>
}
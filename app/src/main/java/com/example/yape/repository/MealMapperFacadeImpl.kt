package com.example.yape.repository

import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.Meal
import com.example.yape.data.model.MealDetail
import com.example.yape.ui.mapper.MealFromIngredientMapper
import com.example.yape.ui.mapper.MealMapper
import com.example.yape.ui.model.MealViewData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealMapperFacadeImpl @Inject constructor(
    private val mealMapper: MealMapper,
    private val mealFromIngredientMapper: MealFromIngredientMapper
) : MealMapperFacade {

    override fun getMealExecuteMapper(baseResponseData: BaseResponseData<MealDetail>):
        List<MealViewData> = mealMapper.executeMapping(baseResponseData)

    override fun getMealFromIngredientExecuteMapper(baseResponseData: BaseResponseData<Meal>):
        List<MealViewData> = mealFromIngredientMapper.executeMapping(baseResponseData)
}
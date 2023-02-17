package com.example.yape.ui.mapper

import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.Meal
import com.example.yape.ui.model.MealViewData
import com.example.yape.ui.util.Mapper
import javax.inject.Inject

class MealFromIngredientMapper @Inject constructor() :
    Mapper<List<MealViewData>, BaseResponseData<Meal>> {

    override fun executeMapping(type: BaseResponseData<Meal>): List<MealViewData> {
        val itemList = type.meals?.map {
            MealViewData(
                idMeal = it.idMeal.orEmpty(),
                strMeal = it.strMeal.orEmpty(),
                strMealThumb = it.strMealThumb.orEmpty()
            )
        }
        return itemList.orEmpty()
    }
}
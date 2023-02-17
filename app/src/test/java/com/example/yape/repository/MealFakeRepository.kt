package com.example.yape.repository

import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.MealDetail
import com.example.yape.data.util.Resource
import com.example.yape.ui.model.MealViewData
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MealFakeRepository : MealRepository {

    private var willReturnError = false
    private var isEmptyMealList = false

    fun setWillReturnError(value: Boolean) {
        willReturnError = value
    }

    fun mealEmptyList(isEmptyList: Boolean) {
        isEmptyMealList = isEmptyList
    }

    override fun getMeals(name: String): Flow<Resource<List<MealViewData>>> {
        return flow {
            val mealsViewData: List<MealViewData> = if (!isEmptyMealList) {
                mockk(relaxed = true)
            } else {
                listOf()
            }
            emit(Resource.Loading())
            if (willReturnError) {
                emit(Resource.Error(mealsViewData))
            } else {
                emit(Resource.Success(mealsViewData))
            }
        }
    }

    override fun getMealsFromIngredient(ingredient: String): Flow<Resource<List<MealViewData>>> {
        return flow {
            val mealsViewData: List<MealViewData> = mockk(relaxed = true)
            emit(Resource.Loading())
            if (willReturnError) {
                emit(Resource.Error())
            } else {
                emit(Resource.Success(mealsViewData))
            }
        }
    }

    override fun getDetailMealFromID(id: String): Flow<Resource<BaseResponseData<MealDetail>>> {
        return flow {
            val mealDetail: BaseResponseData<MealDetail> = mockk(relaxed = true)
            emit(Resource.Loading())
            if (willReturnError) {
                emit(
                    Resource.Error()
                )
            } else {
                emit(Resource.Success(mealDetail))
            }
        }
    }
}
package com.example.yape.repository

import com.example.yape.data.api.RemoteDataSource
import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.Meal
import com.example.yape.data.model.MealDetail
import com.example.yape.data.util.Resource
import com.example.yape.data.util.networkResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MealRepository {

    override fun getMeals(name: String): Flow<Resource<BaseResponseData<MealDetail>>> =
        networkResource(
            networkCall = {
                remoteDataSource.getMeals(name)
            }
        )

    override fun getMealsFromIngredient(ingredient: String): Flow<Resource<BaseResponseData<Meal>>> =
        networkResource(
        networkCall = {
            remoteDataSource.getMealsFromIngredient(ingredient)
        }
    )

    override fun getDetailMealFromID(id: String): Flow<Resource<BaseResponseData<MealDetail>>> =
        networkResource(
            networkCall = {
                remoteDataSource.getDetailMealFromID(id)
            }
        )
}
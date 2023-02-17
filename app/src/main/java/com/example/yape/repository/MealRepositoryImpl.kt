package com.example.yape.repository

import com.example.yape.data.api.meal.RemoteDataSource
import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.MealDetail
import com.example.yape.data.util.Resource
import com.example.yape.data.util.networkResource
import com.example.yape.data.util.networkResourceWithMapper
import com.example.yape.ui.model.MealViewData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val mapperFacade: MealMapperFacade
) : MealRepository {

    override fun getMeals(name: String): Flow<Resource<List<MealViewData>>> =
        networkResourceWithMapper(
            networkCall = {
                remoteDataSource.getMeals(name)
            },
            mapperResponse = {
                mapperFacade.getMealExecuteMapper(it)
            }
        )

    override fun getMealsFromIngredient(ingredient: String): Flow<Resource<List<MealViewData>>> =
        networkResourceWithMapper(
            networkCall = {
                remoteDataSource.getMealsFromIngredient(ingredient)
            },
            mapperResponse = {
                mapperFacade.getMealFromIngredientExecuteMapper(it)
            }
        )

    override fun getDetailMealFromID(id: String): Flow<Resource<BaseResponseData<MealDetail>>> =
        networkResource(
            networkCall = {
                remoteDataSource.getDetailMealFromID(id)
            }
        )
}
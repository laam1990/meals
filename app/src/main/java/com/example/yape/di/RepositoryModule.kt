package com.example.yape.di

import com.example.yape.data.api.map.MapRemoteDataSource
import com.example.yape.data.api.meal.RemoteDataSource
import com.example.yape.repository.MapRepository
import com.example.yape.repository.MapRepositoryImpl
import com.example.yape.repository.MealRepository
import com.example.yape.repository.MealRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMealRepositoryImpl(
        remoteDataSource: RemoteDataSource
    ) = MealRepositoryImpl(
        remoteDataSource
    ) as MealRepository

    @Provides
    @Singleton
    fun provideMapRepositoryImpl(
        remoteDataSource: MapRemoteDataSource
    ) = MapRepositoryImpl(
        remoteDataSource
    ) as MapRepository

}
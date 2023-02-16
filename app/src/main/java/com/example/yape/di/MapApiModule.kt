package com.example.yape.di

import com.example.yape.BuildConfig
import com.example.yape.data.api.map.ApiServiceMap
import com.example.yape.data.api.map.MapRemoteDataSource
import com.example.yape.data.api.map.MapRemoteDataSourceImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapApiModule {

    @Provides
    @Singleton
    @Named("map")
    fun provideMapRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .client(getHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideMapApiService(@Named("map") retrofit: Retrofit): ApiServiceMap =
        retrofit.create(ApiServiceMap::class.java)

    @Provides
    @Singleton
    fun provideMapRemoteDataSource(apiService: ApiServiceMap) =
        MapRemoteDataSourceImpl(apiService) as MapRemoteDataSource

    private fun getHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        //Use the logging interceptor for API response log
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)

        //To set the "Accept-Language"
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            //If we need to know headers, please uncomment the following line
            //httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.HEADERS);
            okHttpClientBuilder
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
        }
        return okHttpClientBuilder.build()
    }
}
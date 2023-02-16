package com.example.yape.data.api.map

import com.example.yape.data.model.LatLngDataItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapRemoteDataSourceImpl @Inject constructor(private val apiService: ApiServiceMap): MapRemoteDataSource {

    override suspend fun getLatLng(code: String): List<LatLngDataItem> =
        apiService.getLatLng(code)
}
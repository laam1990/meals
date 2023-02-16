package com.example.yape.repository

import com.example.yape.data.api.map.MapRemoteDataSource
import com.example.yape.data.model.LatLngDataItem
import com.example.yape.data.util.Resource
import com.example.yape.data.util.networkResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapRepositoryImpl @Inject constructor(
    private val remoteDataSource: MapRemoteDataSource
) : MapRepository {

    override fun getLatLng(code: String): Flow<Resource<List<LatLngDataItem>>> =
        networkResource(
            networkCall = {
                remoteDataSource.getLatLng(code)
            }
        )
}
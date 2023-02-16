package com.example.yape.data.api.map

import com.example.yape.data.model.LatLngDataItem
import retrofit2.http.Path

interface MapRemoteDataSource {

    suspend fun getLatLng(
        @Path("code") code: String
    ): List<LatLngDataItem>

}
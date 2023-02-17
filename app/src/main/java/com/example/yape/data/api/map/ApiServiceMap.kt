package com.example.yape.data.api.map

import com.example.yape.data.model.LatLngDataItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceMap {

    /**
     * get lat and lng to area code
     * https://restcountries.com/v3.1/alpha/{code}
     */
    @GET("v3.1/alpha/{code}")
    suspend fun getLatLng(
        @Path("code") code: String
    ): List<LatLngDataItem>

}
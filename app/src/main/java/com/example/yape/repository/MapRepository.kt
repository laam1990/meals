package com.example.yape.repository

import com.example.yape.data.model.LatLngDataItem
import com.example.yape.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    fun getLatLng(code: String) : Flow<Resource<List<LatLngDataItem>>>
}
package com.example.yape.repository

import com.example.yape.data.model.LatLngDataItem
import com.example.yape.data.util.Resource
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MapFakeRepository : MapRepository {

    private var willReturnError = false

    fun setWillReturnError(value: Boolean) {
        willReturnError = value
    }

    override fun getLatLng(code: String): Flow<Resource<List<LatLngDataItem>>> {
        return flow {
            val latlng: List<LatLngDataItem> = mockk(relaxed = true)
            emit(Resource.Loading())
            if (willReturnError) {
                emit(Resource.Error(latlng))
            } else {
                emit(Resource.Success(latlng))
            }
        }
    }
}
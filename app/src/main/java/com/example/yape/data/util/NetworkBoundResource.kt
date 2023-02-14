package com.example.yape.data.util

import android.util.Log
import kotlinx.coroutines.flow.*

const val TAG = "NetworkBoundResource"

inline fun <RequestType> networkResource(
    crossinline networkCall: suspend () -> RequestType
) = flow {
    emit(Resource.Loading())
    emit(Resource.Success(networkCall()))
}.catch { throwable ->
    Log.w(TAG, "networkResource: Resource.Error", throwable)
    emit(Resource.Error())
}
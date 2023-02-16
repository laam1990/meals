package com.example.yape.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.LatLngDataItem
import com.example.yape.data.model.MealDetail
import com.example.yape.data.util.Resource
import com.example.yape.repository.MapRepository
import com.example.yape.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val repository: MealRepository,
    private val mapRepository: MapRepository
) : ViewModel() {

    private val _detailMealFromIDLiveData = MutableLiveData<Resource<BaseResponseData<MealDetail>>>()
    val detailMealFromIDLiveData: LiveData<Resource<BaseResponseData<MealDetail>>>
        get() = _detailMealFromIDLiveData

    private val _latLngLiveData = MutableLiveData<Resource<List<LatLngDataItem>>>()
    val latLngLiveData: LiveData<Resource<List<LatLngDataItem>>>
        get() = _latLngLiveData

    fun getDetailMealsFromID(id: String) {
        viewModelScope.launch {
            repository.getDetailMealFromID(id).collect {
                _detailMealFromIDLiveData.value = it
            }
        }
    }

    fun getLatLngFromCodeArea(code: String) {
        viewModelScope.launch {
            mapRepository.getLatLng(code).collect {
                _latLngLiveData.value = it
            }
        }
    }
}
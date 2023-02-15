package com.example.yape.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.MealDetail
import com.example.yape.data.util.Resource
import com.example.yape.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private val _detailMealFromIDLiveData = MutableLiveData<Resource<BaseResponseData<MealDetail>>>()
    val detailMealFromIDLiveData: LiveData<Resource<BaseResponseData<MealDetail>>>
        get() = _detailMealFromIDLiveData

    fun getDetailMealsFromID(id: String) {
        viewModelScope.launch {
            repository.getDetailMealFromID(id).collect {
                _detailMealFromIDLiveData.value = it
            }
        }
    }
}
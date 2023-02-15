package com.example.yape.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.Meal
import com.example.yape.data.model.MealDetail
import com.example.yape.data.util.Resource
import com.example.yape.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private val _mealListLiveData = MutableLiveData<Resource<BaseResponseData<MealDetail>>>()
    val mealListLiveData: LiveData<Resource<BaseResponseData<MealDetail>>>
        get() = _mealListLiveData

    private val _mealFromIngredientLiveData = MutableLiveData<Resource<BaseResponseData<Meal>>>()
    val mealFromIngredientLiveData: LiveData<Resource<BaseResponseData<Meal>>>
        get() = _mealFromIngredientLiveData



    fun getMeals(name: String) {
        viewModelScope.launch {
            repository.getMeals(name).collect {
                _mealListLiveData.value = it
            }
        }
    }

    fun getMealsFromIngredient(ingredient: String) {
        viewModelScope.launch {
            repository.getMealsFromIngredient(ingredient).collect {
                _mealFromIngredientLiveData.value = it
            }
        }
    }


}
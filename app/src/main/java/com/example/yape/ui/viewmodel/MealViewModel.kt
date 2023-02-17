package com.example.yape.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yape.data.util.Resource
import com.example.yape.repository.MealRepository
import com.example.yape.ui.action.MealActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private val action = MutableStateFlow<MealActions>(MealActions.Empty)
    fun getAction(): StateFlow<MealActions> = action
    fun getMeals(name: String) {
        viewModelScope.launch {
            repository.getMeals(name).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        action.value = MealActions.ShowError
                    }
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        if (!result.data.isNullOrEmpty()) {
                            action.value = MealActions.ShowSuccess(result.data)
                        } else {
                            action.value = MealActions.EmptyList
                        }
                    }
                }
            }
        }
    }

    fun getMealsFromIngredient(ingredient: String) {
        viewModelScope.launch {
            repository.getMealsFromIngredient(ingredient).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        action.value = MealActions.ShowError
                    }
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        if (!result.data.isNullOrEmpty()) {
                            action.value = MealActions.ShowSuccess(result.data)
                        } else {
                            action.value = MealActions.EmptyList
                        }
                    }
                }
            }
        }
    }


}
package com.example.yape.ui.action

import com.example.yape.ui.model.MealViewData

sealed class MealActions {
    object Empty : MealActions()
    object ShowError : MealActions()
    class ShowSuccess(val listMeal: List<MealViewData>) : MealActions()
    object EmptyList : MealActions()
}

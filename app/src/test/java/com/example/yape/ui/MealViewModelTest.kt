package com.example.yape.ui

import com.example.yape.BaseUnitTest
import com.example.yape.repository.MealFakeRepository
import com.example.yape.ui.action.MealActions
import com.example.yape.ui.viewmodel.MealViewModel
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class MealViewModelTest : BaseUnitTest() {
    private lateinit var mealViewModel: MealViewModel
    private lateinit var repository: MealFakeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = MealFakeRepository()
        mealViewModel = MealViewModel(repository)
    }

    @Test
    fun `should change to error state - when get meal is called`() {
        repository.setWillReturnError(true)

        mealViewModel.getMeals("sushi")

        val value = mealViewModel.getAction().value

        Truth.assertThat(value is MealActions.ShowError).isTrue()
    }

    @Test
    fun `should change to success state - when get meal is called`() {
        mealViewModel.getMeals("sushi")

        val value = mealViewModel.getAction().value

        Truth.assertThat(value is MealActions.ShowSuccess).isTrue()
    }

    @Test
    fun `should change to success empty state - when get meal is called`() {
        repository.mealEmptyList(true)

        mealViewModel.getMeals("sushi")

        val value = mealViewModel.getAction().value

        Truth.assertThat(value is MealActions.EmptyList).isTrue()
    }

    @Test
    fun `should change to error state - when get meal from ingredient is called`() {
        repository.setWillReturnError(true)

        mealViewModel.getMealsFromIngredient("tomato")

        val value = mealViewModel.getAction().value

        Truth.assertThat(value is MealActions.ShowError).isTrue()
    }

    @Test
    fun `should change to success state - when get meal from ingredient is called`() {
        mealViewModel.getMealsFromIngredient("tomato")

        val value = mealViewModel.getAction().value

        Truth.assertThat(value is MealActions.ShowSuccess).isTrue()
    }

    @Test
    fun `should change to success empty state - when get meal from ingredient is called`() {
        repository.mealEmptyList(true)

        mealViewModel.getMeals("tomato")

        val value = mealViewModel.getAction().value

        Truth.assertThat(value is MealActions.EmptyList).isTrue()
    }
}
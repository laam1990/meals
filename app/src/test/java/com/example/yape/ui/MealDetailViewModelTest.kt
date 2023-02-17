package com.example.yape.ui

import androidx.lifecycle.Observer
import com.example.yape.BaseUnitTest
import com.example.yape.data.model.BaseResponseData
import com.example.yape.data.model.LatLngDataItem
import com.example.yape.data.model.MealDetail
import com.example.yape.data.util.Resource
import com.example.yape.getOrAwaitValueTest
import com.example.yape.repository.MapFakeRepository
import com.example.yape.repository.MealFakeRepository
import com.example.yape.ui.viewmodel.MealDetailViewModel
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MealDetailViewModelTest : BaseUnitTest() {
    private lateinit var viewModel: MealDetailViewModel
    private lateinit var repository: MealFakeRepository
    private lateinit var mapRepository: MapFakeRepository

    @Mock
    lateinit var detailMealFromIDObserver: Observer<Resource<BaseResponseData<MealDetail>>>

    @Mock
    lateinit var latLngObserver: Observer<Resource<List<LatLngDataItem>>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = MealFakeRepository()
        mapRepository = MapFakeRepository()
        viewModel = MealDetailViewModel(repository, mapRepository)
        viewModel.apply {
            this.detailMealFromIDLiveData.observeForever(detailMealFromIDObserver)
            this.latLngLiveData.observeForever(latLngObserver)
        }
    }

    @Test
    fun `should change to error state - when get detail meal is called`() {
        repository.setWillReturnError(true)

        viewModel.getDetailMealsFromID("5353")

        val value = viewModel.detailMealFromIDLiveData.getOrAwaitValueTest()

        Truth.assertThat(value is Resource.Error).isTrue()
    }

    @Test
    fun `should change to success state - when get detail meal is called`() {
        viewModel.getDetailMealsFromID("5353")

        val value = viewModel.detailMealFromIDLiveData.getOrAwaitValueTest()

        Truth.assertThat(value is Resource.Success).isTrue()
    }

    @Test
    fun `should change to error state - when get latlng is called`() {
        mapRepository.setWillReturnError(true)

        viewModel.getLatLngFromCodeArea("JP")

        val value = viewModel.latLngLiveData.getOrAwaitValueTest()

        Truth.assertThat(value is Resource.Error).isTrue()
    }

    @Test
    fun `should change to success state - when get latlng is called`() {
        viewModel.getLatLngFromCodeArea("JP")

        val value = viewModel.latLngLiveData.getOrAwaitValueTest()

        Truth.assertThat(value is Resource.Success).isTrue()
    }
}
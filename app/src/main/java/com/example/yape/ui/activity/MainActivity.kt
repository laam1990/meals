package com.example.yape.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.yape.NavGraphMealDirections
import com.example.yape.R
import com.example.yape.data.model.LatLngViewData
import com.example.yape.databinding.ActivityMainBinding
import com.example.yape.ui.fragment.MealDetailFragment
import com.example.yape.ui.fragment.MealFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MealFragment.MealListListener, MealDetailFragment.MealDetailListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun goToDetail(id: String) {
        val action = NavGraphMealDirections.actionToMealDetail(id)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun goToMap(latLngViewData: LatLngViewData) {
        val action = NavGraphMealDirections.actionToMap(latLngViewData)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }
}
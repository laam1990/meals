package com.example.yape.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.yape.data.model.MealDetail
import com.example.yape.data.util.Resource
import com.example.yape.databinding.FragmentMealDetailBinding
import com.example.yape.ui.adapter.IngredientsMealRecyclerViewAdapter
import com.example.yape.ui.viewmodel.MealDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailFragment : Fragment() {

    private var binding: FragmentMealDetailBinding? = null
    private val viewModel: MealDetailViewModel by viewModels()
    private val args: MealDetailFragmentArgs by navArgs()
    private val ingredientsAdapter by lazy { IngredientsMealRecyclerViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding?.apply {
            if (args.strMeal.isNotEmpty()) {
                viewModel.getDetailMealsFromID(args.strMeal)
            }
        }
    }

    private fun initObservers() {
        viewModel.detailMealFromIDLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    result.data?.meals?.let {
                        showSuccessView(it[0])
                    } ?: return@observe
                }
            }
        }
    }

    private fun showSuccessView(mealDetail: MealDetail) {
        binding?.apply {
            ivMeal.load(mealDetail.strMealThumb) {
                crossfade(true)
            }
            tvMeal.text = mealDetail.strMeal
            configureRecyclerView(mealDetail.getIngredientsList())
        }
    }

    private fun configureRecyclerView(list: List<String>) {
        binding?.rvIngredient?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = ingredientsAdapter
            ingredientsAdapter.ingredients = list.toMutableList()
        }
    }
}
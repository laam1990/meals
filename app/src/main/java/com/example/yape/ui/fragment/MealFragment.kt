package com.example.yape.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.yape.data.model.MealDetail
import com.example.yape.data.util.Resource
import com.example.yape.databinding.FragmentMealListBinding
import com.example.yape.ui.adapter.MyItemMealRecyclerViewAdapter
import com.example.yape.ui.viewmodel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class MealFragment : Fragment() {

    private var binding: FragmentMealListBinding? = null
    private var listener: MealListListener? = null
    private val mealAdapter by lazy { MyItemMealRecyclerViewAdapter() }
    private val viewModel: MealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? MealListListener
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initViews() {
        binding?.apply {
            editText.doOnTextChanged { text, _, _, _ ->
                viewModel.getMeals(text.toString().lowercase(Locale.getDefault()))
            }
        }
    }

    private fun initObservers() {
        viewModel.mealListLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    result.data?.meals?.let {
                        configureRecyclerView(it)
                    }?: return@observe
                }
            }
        }
    }

    private fun configureRecyclerView(list: List<MealDetail>) {
        binding?.rvMeals?.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNTER)
            setHasFixedSize(true)
            adapter = mealAdapter
            mealAdapter.meal = list.toMutableList()
        }

        mealAdapter.onItemClick = { it ->
            it.idMeal?.let { id ->
                listener?.goToDetail(id)
            }
        }
    }

    interface MealListListener {
        fun goToDetail(id: String)
    }

    companion object {
        const val COLUMN_COUNTER = 3
    }
}
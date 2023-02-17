package com.example.yape.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.yape.databinding.FragmentMealListBinding
import com.example.yape.ui.action.MealActions
import com.example.yape.ui.adapter.MyItemMealRecyclerViewAdapter
import com.example.yape.ui.model.MealViewData
import com.example.yape.ui.viewmodel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

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
        collectFlows()
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
                if (checkBoxMeal.isChecked) viewModel.getMeals(text.toString().lowercase(Locale.getDefault()))
                if (checkBoxIngredient.isChecked) viewModel.getMealsFromIngredient(text.toString ().lowercase(Locale.getDefault()))
            }

            checkBoxMeal.setOnCheckedChangeListener { _, isChecked ->
                editText.text.clear()
                if (isChecked) {
                    checkBoxIngredient.isChecked = false
                }
            }

            checkBoxIngredient.setOnCheckedChangeListener { _, isChecked ->
                editText.text.clear()
                mealAdapter.meal = emptyList<MealViewData>().toMutableList()
                if (isChecked) {
                    checkBoxMeal.isChecked = false
                }
            }
        }
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            viewModel.getAction().collect { action ->
                when (action) {
                    MealActions.Empty -> Unit
                    MealActions.ShowError -> {}
                    MealActions.EmptyList -> {
                        binding?.apply {
                            rvMeals.visibility = View.GONE
                            lyEmptyOrError.visibility = View.VISIBLE
                        }
                    }
                    is MealActions.ShowSuccess -> {
                        binding?.apply {
                            if (!rvMeals.isVisible) rvMeals.visibility = View.VISIBLE
                            lyEmptyOrError.visibility = View.GONE
                        }
                        configureRecyclerView(action.listMeal)
                    }
                }
            }
        }
    }

    private fun configureRecyclerView(list: List<MealViewData>) {
        binding?.rvMeals?.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNTER)
            setHasFixedSize(true)
            adapter = mealAdapter
            mealAdapter.meal = list.toMutableList()
        }

        mealAdapter.onItemClick = { it ->
            it.idMeal.let { id ->
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
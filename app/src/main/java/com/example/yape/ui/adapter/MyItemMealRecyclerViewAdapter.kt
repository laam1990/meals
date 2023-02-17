package com.example.yape.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.example.yape.databinding.FragmentMealBinding
import com.example.yape.ui.model.MealViewData

class MyItemMealRecyclerViewAdapter : RecyclerView.Adapter<MyItemMealRecyclerViewAdapter.MyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<MealViewData>() {
        override fun areItemsTheSame(
            oldItem: MealViewData,
            newItem: MealViewData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MealViewData,
            newItem: MealViewData
        ): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var meal: MutableList<MealViewData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    var onItemClick: ((MealViewData) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = FragmentMealBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val meal = meal[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int {
        return meal.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class MyViewHolder(
        private val binding: FragmentMealBinding,
        private val onItemClick: ((item: MealViewData) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MealViewData) {
            binding.apply {
                vContentRow.setOnClickListener {
                    onItemClick?.invoke(item)
                }
                ivIcon.load(item.strMealThumb) {
                    crossfade(true)
                }
                tvSubtitle.text = item.strMeal
            }
        }
    }
}
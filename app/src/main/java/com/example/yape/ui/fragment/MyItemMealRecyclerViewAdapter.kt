package com.example.yape.ui.fragment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.example.yape.data.model.MealDetail
import com.example.yape.databinding.FragmentMealBinding

class MyItemMealRecyclerViewAdapter : RecyclerView.Adapter<MyItemMealRecyclerViewAdapter.MyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<MealDetail>() {
        override fun areItemsTheSame(
            oldItem: MealDetail,
            newItem: MealDetail
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MealDetail,
            newItem: MealDetail
        ): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var meal: MutableList<MealDetail>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    var onItemClick: ((MealDetail) -> Unit)? = null

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
        private val onItemClick: ((item: MealDetail) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MealDetail) {
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
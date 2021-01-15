package com.ihadzhi.eatlimination.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ihadzhi.eatlimination.databinding.SearchFoodItemBinding
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto
import com.ihadzhi.eatlimination.ui.FoodSearchAdapter.FoodSearchViewHolder
import com.squareup.picasso.Picasso

typealias SpoonFoodClickListener = (SpoonFoodAuto) -> Unit

internal class FoodSearchAdapter(private val context: Context, private val foodClickListener: SpoonFoodClickListener?) : RecyclerView.Adapter<FoodSearchViewHolder>() {

    private var foods: List<SpoonFoodAuto?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodSearchViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = SearchFoodItemBinding.inflate(layoutInflater, parent, false)
        return FoodSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodSearchViewHolder, position: Int) {
        val food = foods?.get(position)
        holder.bind(food)
    }

    fun setFoods(foods: List<SpoonFoodAuto?>?) {
        this.foods = foods
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return foods?.size ?: 0
    }

    internal inner class FoodSearchViewHolder(var binding: SearchFoodItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(food: SpoonFoodAuto?) {
            binding.food = food
            Picasso.get()
                    .load("https://spoonacular.com/cdn/ingredients_100x100/" + food?.image)
                    .into(binding.foodImage)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (foodClickListener != null && foods?.isNotEmpty() == true) {
                foods?.get(adapterPosition)?.let { foodClickListener.invoke(it) }
            }
        }
    }
}
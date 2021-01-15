package com.ihadzhi.eatlimination.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ihadzhi.eatlimination.data.Food
import com.ihadzhi.eatlimination.databinding.FoodsIncludedItemBinding
import com.ihadzhi.eatlimination.ui.FoodsIncludedAdapter.FoodsIncludedViewHolder
import com.squareup.picasso.Picasso
import java.util.concurrent.Executor

typealias RemoveFromDietListener = (Food?, Int) -> Unit

internal class FoodsIncludedAdapter(private val context: Context, private val foodClickListener: FoodClickListener?, private val removeFromDietListener: RemoveFromDietListener?) : RecyclerView.Adapter<FoodsIncludedViewHolder>() {

    private var foods: MutableList<Food>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsIncludedViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = FoodsIncludedItemBinding.inflate(layoutInflater, parent, false)
        return FoodsIncludedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsIncludedViewHolder, position: Int) {
        val food = foods?.get(position)
        food?.let { holder.bind(it) }
    }

    fun setFoods(foods: List<Food>?) {
        this.foods = foods?.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return foods?.size ?: 0
    }

    fun removeItem(position: Int) {
        foods?.removeAt(position)
        notifyDataSetChanged()
    }

    internal inner class FoodsIncludedViewHolder(var binding: FoodsIncludedItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private val executor: Executor? = null
        fun bind(food: Food) {
            binding.food = food
            Picasso.get()
                    .load("https://spoonacular.com/cdn/ingredients_100x100/" + food.imageUrl)
                    .into(binding.foodImage)
            itemView.setOnClickListener(this)
            binding.foodName.text = food.title
            itemView.setOnLongClickListener { v: View? ->
                if (removeFromDietListener != null) {
                    binding.foodRemove.visibility = View.VISIBLE
                    binding.foodRemove.setOnClickListener { view: View? ->
                        removeFromDietListener.invoke(food, adapterPosition)
                        foods?.remove(food)
                        notifyDataSetChanged()
                    }
                }
                true
            }
            binding.foodRemove.visibility = View.GONE
        }

        override fun onClick(v: View) {
            if (foodClickListener != null && foods?.isNotEmpty() == true) {
                foods?.get(adapterPosition)?.let { foodClickListener.invoke(it) }
            }
        }
    }
}
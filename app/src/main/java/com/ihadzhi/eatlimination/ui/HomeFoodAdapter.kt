package com.ihadzhi.eatlimination.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ihadzhi.eatlimination.data.*
import com.ihadzhi.eatlimination.databinding.HomeFoodItemBinding
import com.ihadzhi.eatlimination.ui.HomeFoodAdapter.HomeFoodViewHolder
import com.squareup.picasso.Picasso
import java.util.concurrent.Executor
import java.util.concurrent.Executors

typealias FoodClickListener = (Food) -> Unit
internal class HomeFoodAdapter(private val context: Context, private val foodClickListener: FoodClickListener?) : RecyclerView.Adapter<HomeFoodViewHolder>() {

    private lateinit var foods: List<Food>
    private val foodDao: FoodDao = EatliminationDatabase.getInstance(context).foodDao()
    private val dietDao: DietDao = EatliminationDatabase.getInstance(context).dietDao()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFoodViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = HomeFoodItemBinding.inflate(layoutInflater, parent, false)
        return HomeFoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFoodViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }

    fun setFoods(foods: List<Food>) {
        this.foods = foods
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    internal inner class HomeFoodViewHolder(var binding: HomeFoodItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var executor: Executor

        fun bind(food: Food) {
            binding.food = food
            Picasso.get()
                    .load("https://spoonacular.com/cdn/ingredients_100x100/" + food.imageUrl)
                    .into(binding.foodImage)
            itemView.setOnClickListener(this)
            if (food.dietId >= 0) {
                binding.addToDietAction.visibility = View.INVISIBLE
            } else {
                binding.addToDietAction.visibility = View.VISIBLE
                binding.addToDietAction.setOnClickListener { view: View? -> addToDiet(food) }
            }
        }

        override fun onClick(v: View) {
            if (foodClickListener != null && foods != null && foods.isNotEmpty()) {
                foodClickListener.invoke(foods[adapterPosition])
            }
        }

        private fun addToDiet(food: Food) {
            dietDao.fetchActiveDiet().observe((context as LifecycleOwner), Observer { activeDiet: Diet? ->
                executor = Executors.newFixedThreadPool(1)
                if (activeDiet != null) {
                    executor.execute(Runnable {
                        food.dietId = activeDiet.id
                        foodDao.updateFood(food)
                        binding.addToDietAction.visibility = View.INVISIBLE
                    })
                }
            })
        }
    }

}
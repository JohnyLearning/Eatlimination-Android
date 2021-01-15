package com.ihadzhi.eatlimination.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.ihadzhi.eatlimination.R
import com.ihadzhi.eatlimination.data.Diet
import com.ihadzhi.eatlimination.data.EatliminationDatabase
import com.ihadzhi.eatlimination.data.Food
import com.ihadzhi.eatlimination.databinding.FragmentAddFoodBinding
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto
import com.squareup.picasso.Picasso
import java.util.*
import java.util.concurrent.Executors

class AddFoodFragment : BaseFragment() {
    private lateinit var food: SpoonFoodAuto
    private lateinit var binding: FragmentAddFoodBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_food, container, false)
        food = AddFoodFragmentArgs.fromBundle(requireArguments()).food
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        showBackButton()
        setTitle(requireContext().getString(R.string.add_food_title, food.name))
        binding.food = food
        binding.addFoodAction.setOnClickListener { viewTemp: View? -> addFoodAction() }
        Picasso.get()
                .load("https://spoonacular.com/cdn/ingredients_500x500/" + food.image)
                .into(binding.foodImage)
    }

    private fun addFoodAction() {
        // add to room
        val foodDao = EatliminationDatabase.getInstance(activity).foodDao()
        val dietDao = EatliminationDatabase.getInstance(activity).dietDao()
        showLoadingIndicator()
        dietDao.fetchActiveDiet().observe(requireActivity(), Observer { activeDiet: Diet? ->
            foodDao.fetchByExternalId(food.id.toLong()).observe(requireActivity(), Observer { foundFoods: List<Food?>? ->
                if (foundFoods != null && foundFoods.isNotEmpty()) {
                    // show alert that food already exists
                } else {
                    Executors.newSingleThreadScheduledExecutor().execute {
                        val createFood = Food(Date(), food.id.toString(), food.image, food.name, -1)
                        foodDao.insert(createFood)
                    }
                    if (NavHostFragment.findNavController(this).currentDestination?.label == "addFoodFragment") {
                        hideLoadingIndicator()
                        NavHostFragment.findNavController(this).navigate(AddFoodFragmentDirections.actionAddFoodFragmentToHomeFragment())
                    }
                }
            })
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavHostFragment.findNavController(this).navigate(AddFoodFragmentDirections.backToFoodSearchFragment())
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
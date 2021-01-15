package com.ihadzhi.eatlimination.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihadzhi.eatlimination.R
import com.ihadzhi.eatlimination.data.Diet
import com.ihadzhi.eatlimination.data.Food
import com.ihadzhi.eatlimination.databinding.FoodsIncludedFragmentBinding
import com.ihadzhi.eatlimination.viewmodel.FoodsIncludedViewModel

class FoodsIncludedFragment : BaseFragment() {
    private lateinit var foodsIncludedViewModel: FoodsIncludedViewModel
    private lateinit var dataBinding: FoodsIncludedFragmentBinding
    private lateinit var foodsIncludedAdapter: FoodsIncludedAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.foods_included_fragment, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setTitle(R.string.foods_included_title)
        foodsIncludedAdapter = FoodsIncludedAdapter(requireActivity(),
                { }) { food: Food?, _ : Int -> food?.let { foodsIncludedViewModel.removeFoodFromDiet(it) } }
        val numberOfItemsPerRow = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 5 else 3
        val layoutManager: LinearLayoutManager = GridLayoutManager(activity, numberOfItemsPerRow)
        dataBinding.foodsIncludedList.layoutManager = layoutManager
        dataBinding.foodsIncludedList.layoutManager = layoutManager
        dataBinding.foodsIncludedList.adapter = foodsIncludedAdapter
        foodsIncludedViewModel = ViewModelProvider(this).get(FoodsIncludedViewModel::class.java)
        foodsIncludedViewModel.activeDiet?.observe(requireActivity(), Observer { activeDiet: Diet? ->
            if (activeDiet != null) {
                foodsIncludedViewModel.getFoods(activeDiet.id)?.observe(requireActivity(), Observer { foods: List<Food>? ->
                    if (foods != null && foods.isNotEmpty()) {
                        foodsIncludedAdapter.setFoods(foods)
                    }
                })
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBackButton()
        showNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
//            if (NavHostFragment.findNavController(this).getCurrentDestination().getLabel() != null &&
//                    NavHostFragment.findNavController(this).getCurrentDestination().getLabel().equals("foodIncludedFragment")) {
            NavHostFragment.findNavController(this).navigate(FoodsIncludedFragmentDirections.foodsIncludedToHomeFragment())
            //            }
        }
        return true
    }
}
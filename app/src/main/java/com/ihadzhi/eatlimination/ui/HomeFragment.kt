package com.ihadzhi.eatlimination.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.ihadzhi.eatlimination.R
import com.ihadzhi.eatlimination.data.Food
import com.ihadzhi.eatlimination.databinding.FragmentHomeBinding
import com.ihadzhi.eatlimination.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {
    private lateinit var dataBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeFoodAdapter: HomeFoodAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.home_title)
        hideBackButton()
        hideNavigation()
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeFoodAdapter = HomeFoodAdapter(requireActivity()) { }
        homeViewModel.foods?.removeObservers(requireActivity())
        homeViewModel.foods?.observe(requireActivity(), Observer { foods: List<Food>? ->
            if (foods != null && foods.isNotEmpty()) {
                setupFoodListContent(foods)
            } else {
                setupNoFoodsUi()
            }
        })
        (dataBinding.foodsList.itemAnimator as SimpleItemAnimator?)?.supportsChangeAnimations = false
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        homeViewModel.activeFoodsCount?.observe(requireActivity(), Observer { count: Int -> menu.setGroupVisible(R.id.diet_group, count > 0) })
        inflater.inflate(R.menu.home_food_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.add_food_action_menu -> addFoodsAction()
            R.id.diet_action_menu -> dietAction()
        }
        return true
    }

    private fun addFoodsAction() {
//        if (NavHostFragment.findNavController(this).getCurrentDestination().getLabel().equals("homeFragment")) {
        NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToFoodSearchFragment())
        //        }
    }

    private fun setupFoodListContent(foods: List<Food>) {
        if (foods != null && foods.size > 0) {
            dataBinding.noFoodsContent.visibility = View.GONE
            dataBinding.addFoodAction.visibility = View.GONE
            dataBinding.foodsList.visibility = View.VISIBLE
            homeFoodAdapter.setFoods(foods)
            val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            dataBinding.foodsList.layoutManager = layoutManager
            dataBinding.foodsList.adapter = homeFoodAdapter
        } else {
            setupNoFoodsUi()
        }
    }

    private fun setupNoFoodsUi() {
        dataBinding.foodsList.visibility = View.GONE
        dataBinding.noFoodsContent.visibility = View.VISIBLE
        dataBinding.addFoodAction.visibility = View.VISIBLE
        dataBinding.addFoodAction.setOnClickListener { button: View? -> addFoodsAction() }
    }

    private fun dietAction() {
//        if (NavHostFragment.findNavController(this).getCurrentDestination().getLabel().equals("homeFragment")) {
        NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToFoodsIncludedFragment())
        //        }
    }
}
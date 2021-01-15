package com.ihadzhi.eatlimination.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihadzhi.eatlimination.R
import com.ihadzhi.eatlimination.databinding.FragmentFoodSearchBinding
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto
import com.ihadzhi.eatlimination.viewmodel.SearchFoodsViewModel

class FoodSearchFragment : BaseFragment() {
    private lateinit var dataBinding: FragmentFoodSearchBinding
    private lateinit var searchFoodsViewModel: SearchFoodsViewModel
    private var foodSearchAdapter: FoodSearchAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_search, container, false)
        setHasOptionsMenu(true)
        //        searchFoodsViewModel = new ViewModelProvider(ViewModelStore::new).get(SearchFoodsViewModel.class);
        searchFoodsViewModel = ViewModelProvider(this).get(SearchFoodsViewModel::class.java)
        setTitle(R.string.search_foods_title)
        return dataBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBackButton()
        dataBinding.searchView.isFocusable = true
        dataBinding.searchView.isIconified = false
        dataBinding.searchView.requestFocus()
        dataBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showLoadingIndicator()
                hideSoftKeyboard()
                try {
                    searchFoodsViewModel.findFoods(query)
                            .observe(requireActivity(), Observer { foods: List<SpoonFoodAuto?>? ->
                                if (foods != null && foods.isNotEmpty()) {
                                    foodSearchAdapter?.setFoods(foods)
                                } else {
                                    showError(R.string.no_foods_found)
                                }
                            })
                } catch (ex: Exception) {
                    showGenericError()
                } finally {
                    dataBinding.searchView.clearFocus()
                    hideLoadingIndicator()
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        foodSearchAdapter = FoodSearchAdapter(requireActivity()) { food: SpoonFoodAuto ->
            NavHostFragment.findNavController(this)
                    .navigate(FoodSearchFragmentDirections.actionFoodSearchFragmentToAddFoodFragment(food))
        }
        dataBinding.foodList.adapter = foodSearchAdapter
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        dataBinding.foodList.layoutManager = layoutManager
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            hideSoftKeyboard()
            NavHostFragment.findNavController(this).navigate(FoodSearchFragmentDirections.actionFoodSearchFragmentToHomeFragment2())
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
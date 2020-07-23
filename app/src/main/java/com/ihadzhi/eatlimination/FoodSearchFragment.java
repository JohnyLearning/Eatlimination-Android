package com.ihadzhi.eatlimination;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.databinding.FragmentFoodSearchBinding;
import com.ihadzhi.eatlimination.viewmodel.SearchFoodsViewModel;

public class FoodSearchFragment extends Fragment {

    private FragmentFoodSearchBinding dataBinding;
    private SearchFoodsViewModel searchFoodsViewModel;
    private FoodSearchAdapter foodSearchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_search, container, false);
        setHasOptionsMenu(true);
//        searchFoodsViewModel = new ViewModelProvider(ViewModelStore::new).get(SearchFoodsViewModel.class);
        searchFoodsViewModel = ViewModelProviders.of(getActivity()).get(SearchFoodsViewModel.class);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBinding.searchView.setFocusable(true);
        dataBinding.searchView.setIconified(false);
        dataBinding.searchView.requestFocus();
        dataBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

              @Override
              public boolean onQueryTextSubmit(String query) {
                  searchFoodsViewModel.findFoods(query).removeObservers(getActivity());
                  searchFoodsViewModel.findFoods(query)
                          .observe(getActivity(), foods -> {
                              if (foods != null && foods.size() > 0) {
                                  foodSearchAdapter.setFoods(foods);
                              } else {

                              }
                          });
                  return true;
              }

              @Override
              public boolean onQueryTextChange(String newText) {
                  return false;
              }

          });
        foodSearchAdapter = new FoodSearchAdapter(getActivity(), food -> {
            NavHostFragment.findNavController(this).navigate(FoodSearchFragmentDirections.actionFoodSearchFragmentToAddFoodFragment(food));
        });
        dataBinding.foodList.setAdapter(foodSearchAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        dataBinding.foodList.setLayoutManager(layoutManager);
    }
}

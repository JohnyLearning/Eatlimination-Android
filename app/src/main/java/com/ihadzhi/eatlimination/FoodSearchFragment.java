package com.ihadzhi.eatlimination;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.ihadzhi.eatlimination.databinding.FragmentFoodSearchBinding;
import com.ihadzhi.eatlimination.databinding.FragmentHomeBinding;
import com.ihadzhi.eatlimination.viewmodel.SearchFoodsViewModel;

public class FoodSearchFragment extends Fragment {

    private FragmentFoodSearchBinding dataBinding;
    private SearchFoodsViewModel searchFoodsViewModel;

    public static FoodSearchFragment newInstance() {

        Bundle args = new Bundle();

        FoodSearchFragment fragment = new FoodSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
                  searchFoodsViewModel.searchFoods(query);
                  return true;
              }

              @Override
              public boolean onQueryTextChange(String newText) {
                  return false;
              }

          });
    }
}

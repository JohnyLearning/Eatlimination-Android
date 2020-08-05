package com.ihadzhi.eatlimination.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.databinding.FoodsIncludedFragmentBinding;
import com.ihadzhi.eatlimination.viewmodel.FoodsIncludedViewModel;
import com.ihadzhi.eatlimination.viewmodel.HomeViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FoodsIncludedFragment extends BaseFragment {

    private FoodsIncludedViewModel foodsIncludedViewModel;
    private FoodsIncludedFragmentBinding dataBinding;
    private FoodsIncludedAdapter foodsIncludedAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.foods_included_fragment, container, false);
        foodsIncludedAdapter = new FoodsIncludedAdapter(getActivity(), food -> {
            // TODO: define action for food selection
        });
        setHasOptionsMenu(true);
        foodsIncludedViewModel = ViewModelProviders.of(this).get(FoodsIncludedViewModel.class);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showBackButton();
        showNavigation();
        foodsIncludedViewModel.getActiveDiet().observe(getActivity(), activeDiet -> {
            if (activeDiet != null) {
                foodsIncludedViewModel.getFoods(activeDiet.getId()).observe(getActivity(), foods -> {
                    if (foods != null && foods.size() > 0) {
                        setupFoodListContent(foods);
                    }
                });
            }
        });
    }

    private void setupFoodListContent(@NotNull List<Food> foods) {
        foodsIncludedAdapter.setFoods(foods);
        int numberOfItemsPerRow = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) ? 5 : 3;
        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), numberOfItemsPerRow);
        dataBinding.foodsIncludedList.setLayoutManager(layoutManager);
        dataBinding.foodsIncludedList.setAdapter(foodsIncludedAdapter);
    }

}
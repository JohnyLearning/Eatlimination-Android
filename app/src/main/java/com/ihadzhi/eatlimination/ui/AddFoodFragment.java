package com.ihadzhi.eatlimination.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.data.DietDao;
import com.ihadzhi.eatlimination.data.EatliminationDatabase;
import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.data.FoodDao;
import com.ihadzhi.eatlimination.databinding.FragmentAddFoodBinding;
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddFoodFragment extends BaseFragment {

    private SpoonFoodAuto food;
    private FragmentAddFoodBinding binding;

    public AddFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_food, container, false);
        food = AddFoodFragmentArgs.fromBundle(getArguments()).getFood();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        showBackButton();
        setTitle(getContext().getString(R.string.add_food_title, food.getName()));
        binding.setFood(food);
        binding.addFoodAction.setOnClickListener(viewTemp -> {
            addFoodAction();
        });
        Picasso.get()
                .load("https://spoonacular.com/cdn/ingredients_500x500/" + food.getImage())
                .into(binding.foodImage);
    }

    private void addFoodAction() {
        // add to room
        FoodDao foodDao = EatliminationDatabase.getInstance(getActivity()).foodDao();
        DietDao dietDao = EatliminationDatabase.getInstance(getActivity()).dietDao();
        showLoadingIndicator();
        dietDao.fetchActiveDiet().observe(getActivity(), activeDiet -> {
            foodDao.fetchByExternalId(food.getId()).observe(getActivity(), foundFoods -> {
                if (foundFoods != null && foundFoods.size() > 0) {
                    // show alert that food already exists
                } else {
                    Executors.newSingleThreadScheduledExecutor().execute(() -> {
                        Food createFood = new Food(new Date(), String.valueOf(food.getId()), food.getImage(), food.getName(), -1);
                        foodDao.insert(createFood);
                    });
                    if (NavHostFragment.findNavController(this).getCurrentDestination().getLabel().equals("addFoodFragment")) {
                        hideLoadingIndicator();
                        NavHostFragment.findNavController(this).navigate(AddFoodFragmentDirections.actionAddFoodFragmentToHomeFragment());
                    }
                }
            });
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavHostFragment.findNavController(this).navigate(AddFoodFragmentDirections.backToFoodSearchFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
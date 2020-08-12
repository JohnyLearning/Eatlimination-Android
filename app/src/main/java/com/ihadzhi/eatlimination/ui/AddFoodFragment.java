package com.ihadzhi.eatlimination.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.data.Diet;
import com.ihadzhi.eatlimination.data.DietDao;
import com.ihadzhi.eatlimination.data.EatliminationDatabase;
import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.data.FoodDao;
import com.ihadzhi.eatlimination.databinding.FragmentAddFoodBinding;
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddFoodFragment extends Fragment {

    private SpoonFoodAuto food;
    private FragmentAddFoodBinding binding;
    private Executor executor;

    public AddFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_food, container, false);
        food = AddFoodFragmentArgs.fromBundle(getArguments()).getFood();
        binding.setFood(food);
        binding.addFoodAction.setOnClickListener(view -> {
            addFoodAction();
        });
        Picasso.get()
                .load("https://spoonacular.com/cdn/ingredients_500x500/" + food.getImage())
                .into(binding.foodImage);
        executor = Executors.newFixedThreadPool(1);
        return binding.getRoot();
    }

    private void addFoodAction() {
        // add to room
        FoodDao foodDao = EatliminationDatabase.getInstance(getActivity()).foodDao();
        DietDao dietDao = EatliminationDatabase.getInstance(getActivity()).dietDao();
        dietDao.fetchActiveDiet().observe(getActivity(), activeDiet -> {
            executor.execute(() -> {
                if (foodDao.fetchByExternalId(food.getId()).getValue() == null) {
                    Food createFood = new Food(new Date(), String.valueOf(food.getId()), food.getImage(), food.getName(), -1);
                    foodDao.insert(createFood);
                } else {
                    // show alert that food already exists
                }
                NavHostFragment.findNavController(this).navigate(AddFoodFragmentDirections.actionAddFoodFragmentToHomeFragment());
            });
        });
    }

}
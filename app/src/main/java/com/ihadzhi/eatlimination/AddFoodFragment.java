package com.ihadzhi.eatlimination;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihadzhi.eatlimination.databinding.FragmentAddFoodBinding;
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto;

public class AddFoodFragment extends Fragment {

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
        binding.setFood(food);
        binding.addFoodAction.setOnClickListener(view -> {
            addFoodAction();
        });
        return binding.getRoot();
    }

    private void addFoodAction() {
        // add to room
        NavHostFragment.findNavController(this).navigate(AddFoodFragmentDirections.actionAddFoodFragmentToHomeFragment());
    }

}
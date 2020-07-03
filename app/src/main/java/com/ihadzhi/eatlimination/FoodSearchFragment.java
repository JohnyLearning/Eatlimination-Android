package com.ihadzhi.eatlimination;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.ihadzhi.eatlimination.databinding.FragmentFoodSearchBinding;
import com.ihadzhi.eatlimination.databinding.FragmentHomeBinding;

public class FoodSearchFragment extends Fragment {

    private FragmentFoodSearchBinding dataBinding;

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
        return dataBinding.getRoot();
    }
}

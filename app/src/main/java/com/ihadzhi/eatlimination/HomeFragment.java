package com.ihadzhi.eatlimination;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.databinding.FragmentHomeBinding;
import com.ihadzhi.eatlimination.viewmodel.HomeViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding dataBinding;
    private HomeViewModel homeViewModel;
    private HomeFoodAdapter homeFoodAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        homeFoodAdapter = new HomeFoodAdapter(getActivity(), food -> {
            // TODO: define action for food selection
        });
        setHasOptionsMenu(true);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel.getActiveDiet().observe(getActivity(), activeDiet -> {
            if (activeDiet != null) {
                homeViewModel.getFoods(activeDiet.getId()).observe(getActivity(), foods -> setupFoodListContent(foods));
            } else {
                setupNoFoodsUi();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_food_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_food_action) {
            addFoodsAction();
            return true;
        } else if (id == R.id.diet_action) {
            dietAction();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addFoodsAction() {
        NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToFoodSearchFragment());
    }

    private void setupFoodListContent(@NotNull List<Food> foods) {
        if (foods != null && foods.size() > 0) {
            dataBinding.noFoodsContent.setVisibility(GONE);
            dataBinding.addFoodAction.setVisibility(GONE);
            dataBinding.foodsList.setVisibility(VISIBLE);
            homeFoodAdapter.setFoods(foods);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            dataBinding.foodsList.setLayoutManager(layoutManager);
            dataBinding.foodsList.setAdapter(homeFoodAdapter);
        } else {
            setupNoFoodsUi();
        }
    }

    private void setupNoFoodsUi() {
        dataBinding.foodsList.setVisibility(GONE);
        dataBinding.noFoodsContent.setVisibility(VISIBLE);
        dataBinding.addFoodAction.setVisibility(VISIBLE);
        dataBinding.addFoodAction.setOnClickListener(button -> {
            addFoodsAction();
        });
    }

    private void dietAction() {
        NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToDietDetailsFragment());
    }
}

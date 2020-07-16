package com.ihadzhi.eatlimination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.databinding.FragmentFoodSearchBinding;

import java.util.List;

public class FoodSearchAdapter extends RecyclerView.Adapter<FoodSearchAdapter.FoodSearchViewHolder> {

    private final Context context;
//    private List<Food> reviews;

    public FoodSearchAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FoodSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        FragmentFoodSearchBinding binding = FragmentFoodSearchBinding.inflate(layoutInflater, parent, false);
        return new FoodSearchViewHolder(parent, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodSearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class FoodSearchViewHolder extends RecyclerView.ViewHolder {

        FragmentFoodSearchBinding binding;

        public FoodSearchViewHolder(@NonNull View itemView, FragmentFoodSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

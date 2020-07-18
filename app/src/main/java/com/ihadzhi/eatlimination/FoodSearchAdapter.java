package com.ihadzhi.eatlimination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.databinding.SearchFoodItemBinding;
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto;

import java.util.List;

class FoodSearchAdapter extends RecyclerView.Adapter<FoodSearchAdapter.FoodSearchViewHolder> {

    private final Context context;
    private List<SpoonFoodAuto> foods;

    public FoodSearchAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FoodSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        SearchFoodItemBinding binding = SearchFoodItemBinding.inflate(layoutInflater, parent, false);
        return new FoodSearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodSearchViewHolder holder, int position) {
        SpoonFoodAuto food = foods.get(position);
        holder.bind(food);
    }

    public void setFoods(List<SpoonFoodAuto> foods) {
        this.foods = foods;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return foods != null ? foods.size() : 0;
    }

    class FoodSearchViewHolder extends RecyclerView.ViewHolder {

        SearchFoodItemBinding binding;

        public FoodSearchViewHolder(SearchFoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(SpoonFoodAuto food) {
            binding.setFood(food);
//            binding.closeAction.setOnClickListener(view -> {
//                dialog.dismiss();
//            });
        }
    }
}

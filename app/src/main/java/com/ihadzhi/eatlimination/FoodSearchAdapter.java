package com.ihadzhi.eatlimination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.databinding.SearchFoodItemBinding;
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto;
import com.squareup.picasso.Picasso;

import java.util.List;

class FoodSearchAdapter extends RecyclerView.Adapter<FoodSearchAdapter.FoodSearchViewHolder> {

    @FunctionalInterface
    public interface FoodClickListener {
        void execute(SpoonFoodAuto food);
    }

    private final Context context;
    private List<SpoonFoodAuto> foods;
    private FoodClickListener foodClickListener;

    public FoodSearchAdapter(Context context, FoodClickListener foodClickListener) {
        this.context = context;
        this.foodClickListener = foodClickListener;
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

    class FoodSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        SearchFoodItemBinding binding;

        public FoodSearchViewHolder(SearchFoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(SpoonFoodAuto food) {
            binding.setFood(food);
            Picasso.get()
                    .load("https://spoonacular.com/cdn/ingredients_100x100/" + food.getImage())
                    .into(binding.foodImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (foodClickListener != null && foods != null && foods.size() > 0) {
                foodClickListener.execute(foods.get(getAdapterPosition()));
            }
        }

    }
}

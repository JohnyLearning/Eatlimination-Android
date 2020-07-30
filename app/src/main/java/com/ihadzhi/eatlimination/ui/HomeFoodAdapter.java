package com.ihadzhi.eatlimination.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.databinding.HomeFoodItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

class HomeFoodAdapter extends RecyclerView.Adapter<HomeFoodAdapter.HomeFoodViewHolder> {

    @FunctionalInterface
    public interface FoodClickListener {
        void execute(Food food);
    }

    private final Context context;
    private List<Food> foods;
    private FoodClickListener foodClickListener;

    public HomeFoodAdapter(Context context, FoodClickListener foodClickListener) {
        this.context = context;
        this.foodClickListener = foodClickListener;
    }

    @NonNull
    @Override
    public HomeFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        HomeFoodItemBinding binding = HomeFoodItemBinding.inflate(layoutInflater, parent, false);
        return new HomeFoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFoodViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.bind(food);
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return foods != null ? foods.size() : 0;
    }

    class HomeFoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        HomeFoodItemBinding binding;

        public HomeFoodViewHolder(HomeFoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Food food) {
            binding.setFood(food);
            Picasso.get()
                    .load("https://spoonacular.com/cdn/ingredients_100x100/" + food.getImageUrl())
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

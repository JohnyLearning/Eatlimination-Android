package com.ihadzhi.eatlimination.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.databinding.FoodsIncludedItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.Executor;

class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.FoodsIncludedViewHolder> {

    @FunctionalInterface
    public interface FoodClickListener {
        void execute(Food food);
    }

    private final Context context;
    private List<Food> foods;
    private FoodClickListener foodClickListener;

    public SymptomsAdapter(Context context, FoodClickListener foodClickListener) {
        this.context = context;
        this.foodClickListener = foodClickListener;
    }

    @NonNull
    @Override
    public FoodsIncludedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        FoodsIncludedItemBinding binding = FoodsIncludedItemBinding.inflate(layoutInflater, parent, false);
        return new FoodsIncludedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsIncludedViewHolder holder, int position) {
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

    class FoodsIncludedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        FoodsIncludedItemBinding binding;
        private Executor executor;

        public FoodsIncludedViewHolder(FoodsIncludedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Food food) {
            binding.setFood(food);
            Picasso.get()
                    .load("https://spoonacular.com/cdn/ingredients_100x100/" + food.getImageUrl())
                    .into(binding.foodImage);
            itemView.setOnClickListener(this);
            binding.foodName.setText(food.getTitle());
        }

        @Override
        public void onClick(View v) {
            if (foodClickListener != null && foods != null && foods.size() > 0) {
                foodClickListener.execute(foods.get(getAdapterPosition()));
            }
        }

    }
}

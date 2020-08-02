package com.ihadzhi.eatlimination.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.data.Diet;
import com.ihadzhi.eatlimination.data.DietDao;
import com.ihadzhi.eatlimination.data.EatliminationDatabase;
import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.data.FoodDao;
import com.ihadzhi.eatlimination.databinding.HomeFoodItemBinding;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class HomeFoodAdapter extends RecyclerView.Adapter<HomeFoodAdapter.HomeFoodViewHolder> {

    @FunctionalInterface
    public interface FoodClickListener {
        void execute(Food food);
    }

    private final Context context;
    private List<Food> foods;
    private FoodClickListener foodClickListener;
    private FoodDao foodDao;
    private DietDao dietDao;

    public HomeFoodAdapter(Context context, FoodClickListener foodClickListener) {
        this.context = context;
        this.foodClickListener = foodClickListener;
        foodDao = EatliminationDatabase.getInstance(context).foodDao();
        dietDao = EatliminationDatabase.getInstance(context).dietDao();
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
        private Executor executor;

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
            if (food.getDietId() >= 0) {
                binding.addToDiet.setVisibility(View.GONE);
            } else {
                binding.addToDiet.setVisibility(View.VISIBLE);
                binding.addToDiet.setOnClickListener(view -> {
                    addToDiet(food);
                });
            }
        }

        @Override
        public void onClick(View v) {
            if (foodClickListener != null && foods != null && foods.size() > 0) {
                foodClickListener.execute(foods.get(getAdapterPosition()));
            }
        }

        private void addToDiet(Food food) {
            dietDao.fetchActiveDiet().observe((LifecycleOwner) context, activeDiet -> {
                executor = Executors.newFixedThreadPool(1);
                if (activeDiet == null) {
                    executor.execute(() -> {
                        long dietId = dietDao.insert(new Diet(true, new Date()));
                        foodDao.updateFoodDiet(food.getId(), dietId);
                    });
                } else {
                    executor.execute(() -> {
                        foodDao.updateFoodDiet(food.getId(), activeDiet.getId());
                    });
                }
                binding.addToDiet.setVisibility(View.GONE);
            });
        }

    }
}

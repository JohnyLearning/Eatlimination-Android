package com.ihadzhi.eatlimination.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ihadzhi.eatlimination.data.Diet;
import com.ihadzhi.eatlimination.data.Food;

import java.util.List;
import java.util.concurrent.Executors;

public class AddFoodViewModel extends BaseViewModel {

    private LiveData<List<Food>> foods;
    private LiveData<Diet> activeDiet;

    public AddFoodViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Diet> getActiveDiet() {
        if (activeDiet == null) {
            activeDiet = database.dietDao().fetchActiveDiet();
        }
        return activeDiet;
    }

    @Nullable
    public LiveData<List<Food>> getFoods(long dietId) {
        if (foods == null) {
            foods = new MutableLiveData<>();
            foods = database.foodDao().fetchByDietId(dietId);
        };
        return foods;
    }

    public void removeFoodFromDiet(Food food) {
        food.setDietId(-1);
        Executors.newSingleThreadScheduledExecutor().execute(() -> database.foodDao().updateFood(food));

    }
}
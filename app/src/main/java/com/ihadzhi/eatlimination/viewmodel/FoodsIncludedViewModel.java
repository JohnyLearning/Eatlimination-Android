package com.ihadzhi.eatlimination.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ihadzhi.eatlimination.data.Diet;
import com.ihadzhi.eatlimination.data.EatliminationDatabase;
import com.ihadzhi.eatlimination.data.Food;

import java.util.List;

public class FoodsIncludedViewModel extends BaseViewModel {

    private LiveData<List<Food>> foods;
    private LiveData<Diet> activeDiet;

    public FoodsIncludedViewModel(@NonNull Application application) {
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
}
package com.ihadzhi.eatlimination.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ihadzhi.eatlimination.data.Diet;
import com.ihadzhi.eatlimination.data.EatliminationDatabase;
import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.network.SpoonacularService;
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    private LiveData<List<Food>> foods;
    private LiveData<Integer> activeFoodsCount;
    private Executor executor;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    @Nullable
    public LiveData<List<Food>> getFoods() {
        if (foods == null) {
            foods = new MutableLiveData<>();
            foods = database.foodDao().getAll();
        }
        return foods;
    }

    public LiveData<Integer> getActiveFoodsCount() {
        activeFoodsCount = database.foodDao().getActiveFoodsCount();
        return activeFoodsCount;
    }
}

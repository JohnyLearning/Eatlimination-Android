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

public class HomeViewModel extends AndroidViewModel {

    private LiveData<List<Food>> foods;
    private LiveData<Diet> activeDiet;
    private Executor executor;
    private EatliminationDatabase db;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        db = EatliminationDatabase.getInstance(getApplication().getApplicationContext());
    }

    public LiveData<Diet> getActiveDiet() {
        if (activeDiet == null) {
            activeDiet = db.dietDao().fetchActiveDiet();
        }
        return activeDiet;
    }

    @Nullable
    public LiveData<List<Food>> getFoods(long activeDietId) {
        if (foods == null) {
            foods = new MutableLiveData<>();
            foods = db.foodDao().fetchByDietId(activeDietId);
        }
        return foods;
    }
}

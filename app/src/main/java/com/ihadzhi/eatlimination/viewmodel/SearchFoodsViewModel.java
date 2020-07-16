package com.ihadzhi.eatlimination.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ihadzhi.eatlimination.network.SpoonacularService;
import com.ihadzhi.eatlimination.network.model.SpoonFoodAuto;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchFoodsViewModel extends AndroidViewModel {

    private MutableLiveData<List<SpoonFoodAuto>> foundFoods;
    private SpoonacularService spoonacularService;


    public SearchFoodsViewModel(@NonNull Application application) {
        super(application);
        spoonacularService = new SpoonacularService();
    }

    public void searchFoods(CharSequence searchFoodsQuery) {
        if (foundFoods == null) {
            foundFoods = new MutableLiveData<>();
            spoonacularService.searchFoods(searchFoodsQuery)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(foundFoodsFromResponse ->
                        foundFoods.setValue(foundFoodsFromResponse),
                            throwable -> {
                                // TODO: handle error
                            });
        }
    }
}

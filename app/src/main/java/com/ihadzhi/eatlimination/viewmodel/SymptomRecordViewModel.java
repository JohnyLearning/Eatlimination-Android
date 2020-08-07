package com.ihadzhi.eatlimination.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.data.Symptom;

import java.util.List;

public class SymptomsViewModel extends BaseViewModel {

    private LiveData<List<Symptom>> symptoms;

    public SymptomsViewModel(@NonNull Application application) {
        super(application);
    }

    @Nullable
    public LiveData<List<Symptom>> getSymptoms() {
        if (symptoms == null) {
            symptoms = new MutableLiveData<>();
            symptoms = database.symptomDao().getAll();
        };
        return symptoms;
    }

}
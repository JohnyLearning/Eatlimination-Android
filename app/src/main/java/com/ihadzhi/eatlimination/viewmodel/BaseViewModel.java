package com.ihadzhi.eatlimination.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ihadzhi.eatlimination.data.EatliminationDatabase;

public class BaseViewModel extends AndroidViewModel {

    protected EatliminationDatabase database;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        database = EatliminationDatabase.getInstance(application.getApplicationContext());
    }
}

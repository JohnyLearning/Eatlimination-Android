package com.ihadzhi.eatlimination.viewmodel;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ihadzhi.eatlimination.data.Diet;
import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.data.SymptomRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import static com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory.green;
import static com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory.red;
import static com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory.yellow;

public class ViewRecordingsViewModel extends BaseViewModel {

    private LiveData<List<SymptomRecord>> recordings;
//    private LiveData<Diet> activeDiet;
//    private Executor executor;

    public ViewRecordingsViewModel(@NonNull Application application) {
        super(application);
    }

    @Nullable
    public LiveData<List<SymptomRecord>> getRecordings(long symptomId) {
//        if (recordings == null) {
            recordings = new MutableLiveData<>();
            recordings = database.symptomRecordDao().fetchBySymptomId(symptomId);
//        }
        return recordings;
    }
}

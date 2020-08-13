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

    private MutableLiveData<List<SymptomRecord>> recordings;
    private LiveData<Diet> activeDiet;
    private Executor executor;

    public ViewRecordingsViewModel(@NonNull Application application) {
        super(application);
    }

    @Nullable
    public LiveData<List<SymptomRecord>> getRecordings() {
        if (recordings == null) {
            recordings = new MutableLiveData<>();
//            recordings = database.symptomDao().getAll();
            Handler myHandler = new Handler();
            myHandler.postDelayed(() -> {
                List<SymptomRecord> tempRecordings = new ArrayList<>();
                tempRecordings.add(new SymptomRecord(1, green, "120/80", new Date(), 100));
                tempRecordings.add(new SymptomRecord(2, yellow, "150/90", new Date(), 100));
                tempRecordings.add(new SymptomRecord(3, yellow, "140/90", new Date(), 100));
                tempRecordings.add(new SymptomRecord(4, red, "180/100", new Date(), 100));
                tempRecordings.add(new SymptomRecord(4, green, "130/80", new Date(), 100));
                recordings.setValue(tempRecordings);
            }, 500);

        }
        return recordings;
    }
}

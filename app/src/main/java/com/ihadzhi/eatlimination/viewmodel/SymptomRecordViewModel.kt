package com.ihadzhi.eatlimination.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ihadzhi.eatlimination.data.Symptom

class SymptomRecordViewModel(application: Application) : BaseViewModel(application) {

    var symptoms: LiveData<List<Symptom>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
                field = database.symptomDao().all
            }
            return field
        }
        private set

}
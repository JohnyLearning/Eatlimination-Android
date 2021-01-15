package com.ihadzhi.eatlimination.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ihadzhi.eatlimination.data.SymptomRecord

class ViewRecordingsViewModel(application: Application) : BaseViewModel(application) {

    private var recordings: LiveData<List<SymptomRecord>>? = null

    fun getRecordings(symptomId: Long): LiveData<List<SymptomRecord>>? {
//        if (recordings == null) {
        recordings = MutableLiveData()
        recordings = database.symptomRecordDao().fetchBySymptomId(symptomId)
        //        }
        return recordings
    }

}
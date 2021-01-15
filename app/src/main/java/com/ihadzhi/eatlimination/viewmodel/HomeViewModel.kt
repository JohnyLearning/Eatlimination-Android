package com.ihadzhi.eatlimination.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ihadzhi.eatlimination.data.Food
import java.util.concurrent.Executor

class HomeViewModel(application: Application) : BaseViewModel(application) {
    var foods: LiveData<List<Food>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
                field = database.foodDao().all
            }
            return field
        }
        private set
    var activeFoodsCount: LiveData<Int>? = null
        get() {
            field = database.foodDao().activeFoodsCount
            return field
        }
        private set

}
package com.ihadzhi.eatlimination.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ihadzhi.eatlimination.data.EatliminationDatabase

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    @JvmField
    protected var database: EatliminationDatabase = EatliminationDatabase.getInstance(application.applicationContext)

}
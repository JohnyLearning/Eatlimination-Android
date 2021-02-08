package com.ihadzhi.eatlimination.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SymptomDao {
    @get:Query("SELECT * FROM symptom ORDER BY name")
    val all: LiveData<List<Symptom>>?

    @Insert
    fun insertAll(vararg symptoms: Symptom?)
}
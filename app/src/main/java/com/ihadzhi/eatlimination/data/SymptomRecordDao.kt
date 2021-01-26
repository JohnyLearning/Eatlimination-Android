package com.ihadzhi.eatlimination.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SymptomRecordDao {
    @get:Query("SELECT * FROM SymptomRecord ORDER BY timestamp DESC")
    val all: LiveData<List<SymptomRecord?>?>?

    @Query("SELECT * FROM SymptomRecord WHERE symptomId = :symptomId ORDER BY timestamp DESC")
    fun fetchBySymptomId(symptomId: Long): LiveData<List<SymptomRecord?>?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(symptomRecord: SymptomRecord?): Long

    @Delete
    fun delete(symptomRecord: SymptomRecord?)
}
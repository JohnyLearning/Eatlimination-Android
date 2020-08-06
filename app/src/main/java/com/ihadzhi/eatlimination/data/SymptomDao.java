package com.ihadzhi.eatlimination.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SymptomDao {

    @Query("SELECT * FROM symptom ORDER BY name")
    LiveData<List<Symptom>> getAll();

    @Insert
    void insertAll(Symptom ... symptoms);

}

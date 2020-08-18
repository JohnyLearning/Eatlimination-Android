package com.ihadzhi.eatlimination.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SymptomRecordDao {

    @Query("SELECT * FROM SymptomRecord ORDER BY timestamp DESC")
    LiveData<List<SymptomRecord>> getAll();

    @Query("SELECT * FROM SymptomRecord WHERE symptomId = :symptomId ORDER BY timestamp DESC")
    LiveData<List<SymptomRecord>> fetchBySymptomId(long symptomId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(SymptomRecord symptomRecord);

    @Delete
    void delete(SymptomRecord symptomRecord);

}

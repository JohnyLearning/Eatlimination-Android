package com.ihadzhi.eatlimination.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DietDao {

    @Query("SELECT * FROM Diet WHERE active = 1 ORDER BY createdAt DESC LIMIT 1")
    LiveData<Diet> fetchActiveDiet();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Diet diet);

    @Delete
    void delete(Diet diet);

}

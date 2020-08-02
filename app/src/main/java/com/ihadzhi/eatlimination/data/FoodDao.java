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
public interface FoodDao {

    @Query("SELECT * FROM food ORDER BY createdAt DESC")
    LiveData<List<Food>> getAll();

    @Query("SELECT * FROM Food WHERE dietId = :dietId ORDER BY createdAt DESC")
    LiveData<List<Food>> fetchByDietId(long dietId);

    @Query("SELECT * FROM Food WHERE externalId = :externalId ORDER BY createdAt DESC")
    LiveData<List<Food>> fetchByExternalId(long externalId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Food food);

    @Query("UPDATE Food SET dietId = :dietId WHERE id = :id")
    int updateFoodDiet(long id, long dietId);

    @Delete
    void delete(Food food);

}

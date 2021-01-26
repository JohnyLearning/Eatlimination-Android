package com.ihadzhi.eatlimination.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodDao {
    @get:Query("SELECT * FROM food ORDER BY createdAt DESC")
    val all: LiveData<List<Food?>?>?

    @Query("SELECT * FROM Food WHERE dietId = :dietId ORDER BY createdAt DESC")
    fun fetchByDietId(dietId: Long): LiveData<List<Food?>?>?

    @Query("SELECT * FROM Food WHERE externalId = :externalId ORDER BY createdAt DESC")
    fun fetchByExternalId(externalId: Long): LiveData<List<Food?>?>?

    @get:Query("SELECT COUNT(id) FROM Food WHERE dietId > -1")
    val activeFoodsCount: LiveData<Int?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: Food?): Long

    @Update
    fun updateFood(food: Food?): Int

    @Delete
    fun delete(food: Food?)
}
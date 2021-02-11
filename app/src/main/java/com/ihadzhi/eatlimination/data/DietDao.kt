package com.ihadzhi.eatlimination.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DietDao {
    @Query("SELECT * FROM Diet WHERE isActive > 0 ORDER BY createdAt DESC")
    fun fetchActiveDiet(): LiveData<Diet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(diet: Diet?): Long

    @Delete
    fun delete(diet: Diet?)
}
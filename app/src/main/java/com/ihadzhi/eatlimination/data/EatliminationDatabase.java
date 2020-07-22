package com.ihadzhi.eatlimination.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Food.class, Diet.class, Symptom.class, SymptomRecord.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class EatliminationDatabase extends RoomDatabase {

    private static final String DB_NAME = "eatlimination";

    private static EatliminationDatabase instance;

    public static EatliminationDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (EatliminationDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            EatliminationDatabase.class, DB_NAME)
//                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract FoodDao foodDao();

    public abstract DietDao dietDao();

}

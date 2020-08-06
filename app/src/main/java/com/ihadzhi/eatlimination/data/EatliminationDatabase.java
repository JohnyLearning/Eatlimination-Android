package com.ihadzhi.eatlimination.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ihadzhi.eatlimination.R;

import java.util.concurrent.Executors;

@Database(entities = {Food.class, Diet.class, Symptom.class, SymptomRecord.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class EatliminationDatabase extends RoomDatabase {

    private static final Symptom[] symptoms = new Symptom[] {
        new Symptom(100, "High blood pressure", "Common condition in which the long-term force of the blood against your artery walls is high enough that it may eventually cause health problems", R.drawable.hbp),
        new Symptom(200, "Heart rate", "The speed of the heartbeat measured by the number of contractions of the heart per minute", R.drawable.heartbeat),
        new Symptom(300, "Headache", "The symptom of pain in the face, head, or neck", R.drawable.headache),
        new Symptom(400, "Tinea Versicolor", "Fungal infection of the skin, caused by a type of yeast that naturally lives on your skin.", R.drawable.tineaversicolor)
    };

    private static final String DB_NAME = "eatlimination";

    private static EatliminationDatabase instance;

    private static Context mContext;

    public static EatliminationDatabase getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            synchronized (EatliminationDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            EatliminationDatabase.class, DB_NAME)
//                            .allowMainThreadQueries()
                            .addCallback(initCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback initCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    Executors.newSingleThreadScheduledExecutor().execute(() -> {
                        EatliminationDatabase.getInstance(mContext).symptomDao().insertAll(symptoms);
                    });
                }

            };

    public abstract FoodDao foodDao();

    public abstract DietDao dietDao();

    public abstract SymptomDao symptomDao();

}

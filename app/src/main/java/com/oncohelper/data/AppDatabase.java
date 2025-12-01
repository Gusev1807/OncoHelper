package com.oncohelper.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.oncohelper.models.Medication;

@Database(entities = {Medication.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract MedicationDao medicationDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "oncohelper_database"
                    ).fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

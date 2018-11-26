package com.rshtukaraxondevgroup.bluetoothtest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.rshtukaraxondevgroup.bluetoothtest.model.PhotoModel;

import static com.rshtukaraxondevgroup.bluetoothtest.Constants.DATABASE;

@Database(entities = {PhotoModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PhotoDao bookDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
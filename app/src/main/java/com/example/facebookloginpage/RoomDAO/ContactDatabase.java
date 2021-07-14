package com.example.facebookloginpage.RoomDAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Contact2.class, exportSchema = false, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    private static final String database_name = "SampleApp.db";
    private static ContactDatabase instance;

    public static synchronized ContactDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ContactDatabase.class, database_name)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract ContactDAO contactDAO();
}

package com.example.facebookloginpage.ToDoList;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.facebookloginpage.ToDoList.ToDoList;

@Database(entities = ToDoList.class, exportSchema = false, version = 1)
public abstract class ToDoListDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "Todolist.db";
    private static ToDoListDatabase instance;

    public static synchronized ToDoListDatabase getInstance(Context context){
        if (instance != null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ToDoListDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigrationOnDowngrade()
                    .build();
        }
        return instance;
    }

    public abstract ToDoListDAO ToDoListDAO();
}

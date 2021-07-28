package com.example.facebookloginpage.ToDoList;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoListDAO {
    @Query("SELECT * from todolist")
    List<ToDoList> getAllTasks();

    @Insert
    void insertTask(ToDoList todolist);

    @Update
    void updateTask(ToDoList todolist);

    @Delete
    void deleteTask(ToDoList todolist);
}

package com.example.facebookloginpage.RoomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {
    @Query("Select * from contacts")
    List<Contact2> getAllContacts();

    @Insert
    void insertContact(Contact2 contact2);

    @Update
    void updateContact(Contact2 contact2);

    @Delete
    void deleteContact(Contact2 contact2);
}

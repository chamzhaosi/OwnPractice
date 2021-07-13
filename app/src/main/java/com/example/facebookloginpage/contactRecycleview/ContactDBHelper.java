package com.example.facebookloginpage.contactRecycleview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ContactDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SampleAppDB.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER = "number";

    public ContactDBHelper(Context context){
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //"CREATE TABLE" + TABLE_NAME +  "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT, " + COLUMN_NUMBER + " TEXT)"
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_NUMBER + " TEXT)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    //CRUD create, read, update, delete implementation
    //create keyword: insert
    public void insertContact (Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, contact.getName());
        contentValues.put(COLUMN_NUMBER, contact.getPhoneNumber());
        db.insert(TABLE_NAME, null, contentValues);
    }

    //read keyword:rowQuery
    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contactsList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        result.moveToFirst();

        while (!result.isAfterLast()){
            Contact contact = new Contact();
            int id = result.getInt(result.getColumnIndex(COLUMN_ID));
            String name = result.getString(result.getColumnIndex(COLUMN_NAME));
            String number = result.getString(result.getColumnIndex(COLUMN_NUMBER));

            contact.setId(id);
            contact.setName(name);
            contact.setPhoneNumber(number);

            contactsList.add(contact);

            result.moveToNext();
        }

        result.close();

        return contactsList;
    }

    //Update keyword: update
    public void updateContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, contact.getName());
        contentValues.put(COLUMN_NUMBER, contact.getPhoneNumber());

        String stringId = Integer.toString(contact.getId());

        db.update(TABLE_NAME, contentValues, "id=" + stringId, null);
    }

    //Delete keyword:delete
    public void deleteContact(int id){
        String stringId = Integer.toString(id);

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id=" + stringId, null);
    }
}

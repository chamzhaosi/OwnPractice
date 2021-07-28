package com.example.facebookloginpage.ToDoList;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "todolist")
public class ToDoList {
    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo (name = "subject")
    private String subject;
    @ColumnInfo (name =  "detail")
    private String detail;
    @ColumnInfo (name = "duedate")
    private String dueDate;

    public ToDoList(int id, String subject, String detail, String dueDate) {
        this.id = id;
        this.subject = subject;
        this.detail = detail;
        this.dueDate = dueDate;
    }

    @Ignore
    public ToDoList(String subject, String detail, String dueDate) {
        this.subject = subject;
        this.detail = detail;
        this.dueDate = dueDate;
    }

    @Ignore
    public ToDoList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}

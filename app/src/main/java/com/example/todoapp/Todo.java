package com.example.todoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todos")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String description;
    public String dueDate;
    public boolean completed;

    public int userId;
}
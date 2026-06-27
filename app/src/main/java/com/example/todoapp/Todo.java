//package com.example.todoapp;
//
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//@Entity(tableName = "todos")
//public class Todo {
//
//    @PrimaryKey(autoGenerate = true)
//    public int id;
//
//    public String title;
//    public String description;
//    public String dueDate;
//    public boolean completed;
//
//    public int userId;
//}


package com.example.todoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todos")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;

    public String description;

    // NEW
    public String dueDate;

    // NEW
    public String tags;

    // NEW
    public String priority;

    public boolean completed;

    public int userId;

    // NEW
    public long createdAt;

    // NEW
    public long updatedAt;
}
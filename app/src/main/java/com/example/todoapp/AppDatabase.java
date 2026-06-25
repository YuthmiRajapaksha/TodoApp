//package com.example.todoapp;
//
//import androidx.room.Database;
//import androidx.room.RoomDatabase;
//
//@Database(entities = {Todo.class}, version = 1)
//public abstract class AppDatabase extends RoomDatabase {
//
//    public abstract TodoDao todoDao();
//
//}


package com.example.todoapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Todo.class, User.class},
        version = 2
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();

    public abstract UserDao userDao();
}
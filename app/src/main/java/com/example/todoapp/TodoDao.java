//package com.example.todoapp;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import java.util.List;
//
//@Dao
//public interface TodoDao {
//
//    @Insert
//    void insert(Todo todo);
//
//    @Update
//    void update(Todo todo);
//
//    @Delete
//    void delete(Todo todo);
//
//    @Query("SELECT * FROM todos")
//    List<Todo> getAllTodos();
//}


//package com.example.todoapp;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import java.util.List;
//
//@Dao
//public interface TodoDao {
//
//    @Insert
//    void insert(Todo todo);
//
//    @Update
//    void update(Todo todo);
//    @Query("SELECT * FROM todos WHERE id = :id")
//    Todo getTodoById(int id);
//
//    @Delete
//    void delete(Todo todo);
//
//    @Query("SELECT * FROM todos")
//    List<Todo> getAllTodos();
//
//    @Query("SELECT * FROM todos WHERE id = :id")
//    Todo getTodoById(int id);
//
//    @Query("SELECT * FROM todos WHERE userId = :userId")
//    List<Todo> getTodosByUser(int userId);
//}


//package com.example.todoapp;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import java.util.List;
//
//@Dao
//public interface TodoDao {
//
//    @Insert
//    void insert(Todo todo);
//
//    @Update
//    void update(Todo todo);
//
//    @Delete
//    void delete(Todo todo);
//
//    @Query("SELECT * FROM todos")
//    List<Todo> getAllTodos();
//
//    @Query("SELECT * FROM todos WHERE id = :id")
//    Todo getTodoById(int id);
//
//    @Query("SELECT * FROM todos WHERE userId = :userId")
//    List<Todo> getTodosByUser(int userId);
//}


package com.example.todoapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("SELECT * FROM todos WHERE id=:id")
    Todo getTodoById(int id);

    @Query("SELECT * FROM todos WHERE userId=:userId")
    List<Todo> getTodosByUser(int userId);

}
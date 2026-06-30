////package com.example.todoapp;
////
////import androidx.room.Dao;
////import androidx.room.Delete;
////import androidx.room.Insert;
////import androidx.room.Query;
////import androidx.room.Update;
////
////import java.util.List;
////
////@Dao
////public interface TodoDao {
////
////    @Insert
////    void insert(Todo todo);
////
////    @Update
////    void update(Todo todo);
////
////    @Delete
////    void delete(Todo todo);
////
////    @Query("SELECT * FROM todos")
////    List<Todo> getAllTodos();
////}
//
//
////package com.example.todoapp;
////
////import androidx.room.Dao;
////import androidx.room.Delete;
////import androidx.room.Insert;
////import androidx.room.Query;
////import androidx.room.Update;
////
////import java.util.List;
////
////@Dao
////public interface TodoDao {
////
////    @Insert
////    void insert(Todo todo);
////
////    @Update
////    void update(Todo todo);
////    @Query("SELECT * FROM todos WHERE id = :id")
////    Todo getTodoById(int id);
////
////    @Delete
////    void delete(Todo todo);
////
////    @Query("SELECT * FROM todos")
////    List<Todo> getAllTodos();
////
////    @Query("SELECT * FROM todos WHERE id = :id")
////    Todo getTodoById(int id);
////
////    @Query("SELECT * FROM todos WHERE userId = :userId")
////    List<Todo> getTodosByUser(int userId);
////}
//
//
////package com.example.todoapp;
////
////import androidx.room.Dao;
////import androidx.room.Delete;
////import androidx.room.Insert;
////import androidx.room.Query;
////import androidx.room.Update;
////
////import java.util.List;
////
////@Dao
////public interface TodoDao {
////
////    @Insert
////    void insert(Todo todo);
////
////    @Update
////    void update(Todo todo);
////
////    @Delete
////    void delete(Todo todo);
////
////    @Query("SELECT * FROM todos")
////    List<Todo> getAllTodos();
////
////    @Query("SELECT * FROM todos WHERE id = :id")
////    Todo getTodoById(int id);
////
////    @Query("SELECT * FROM todos WHERE userId = :userId")
////    List<Todo> getTodosByUser(int userId);
////}
//
//
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
//    @Query("SELECT * FROM todos WHERE id=:id")
//    Todo getTodoById(int id);
//
//    @Query("SELECT * FROM todos WHERE userId=:userId")
//    List<Todo> getTodosByUser(int userId);
//
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

    @Query("SELECT * FROM todos WHERE id = :id")
    Todo getTodoById(int id);

    @Query("SELECT * FROM todos WHERE userId = :userId")
    List<Todo> getTodosByUser(int userId);

    @Query("SELECT * FROM todos WHERE userId = :userId AND (title LIKE '%' || :search || '%' OR description LIKE '%' || :search || '%')")
    List<Todo> searchTodos(int userId, String search);

    // Sort by Priority (High → Medium → Low)
    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY CASE priority " +
            "WHEN 'High' THEN 1 " +
            "WHEN 'Medium' THEN 2 " +
            "WHEN 'Low' THEN 3 " +
            "ELSE 4 END")
    List<Todo> getTodosByPriority(int userId);

    // Sort by Due Date
    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY dueDate ASC")
    List<Todo> sortByDueDate(int userId);

    // Sort by Title
    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY title ASC")
    List<Todo> sortByTitle(int userId);

    // Sort by Newest
    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY createdAt DESC")
    List<Todo> sortByNewest(int userId);

    // Sort by Completed
    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY completed DESC")
    List<Todo> sortByCompleted(int userId);

}

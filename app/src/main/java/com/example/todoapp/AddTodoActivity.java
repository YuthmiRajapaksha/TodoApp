////package com.example.todoapp;
////
////import android.os.Bundle;
////
////import androidx.activity.EdgeToEdge;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.core.graphics.Insets;
////import androidx.core.view.ViewCompat;
////import androidx.core.view.WindowInsetsCompat;
////
////public class AddTodoActivity extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        EdgeToEdge.enable(this);
////        setContentView(R.layout.activity_add_todo);
////        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
////            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
////            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
////            return insets;
////        });
////    }
////}
//
//
//
////package com.example.todoapp;
////
////import android.os.Bundle;
////import androidx.appcompat.app.AppCompatActivity;
////
////public class AddTodoActivity extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_add_todo);
////    }
////}
//
////package com.example.todoapp;
////
////import android.os.Bundle;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.Toast;
////
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.room.Room;
////
////public class AddTodoActivity extends AppCompatActivity {
////
////    EditText etTitle, etDescription;
////    Button btnSave;
////
////    AppDatabase db;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_add_todo);
////
////        etTitle = findViewById(R.id.etTitle);
////        etDescription = findViewById(R.id.etDescription);
////        btnSave = findViewById(R.id.btnSave);
////
////        db = Room.databaseBuilder(
////                getApplicationContext(),
////                AppDatabase.class,
////                "todo_database"
////        ).allowMainThreadQueries().build();
////
////        btnSave.setOnClickListener(v -> {
////
////            String title = etTitle.getText().toString().trim();
////            String description = etDescription.getText().toString().trim();
////
////            if (title.isEmpty()) {
////                Toast.makeText(this, "Enter a title", Toast.LENGTH_SHORT).show();
////                return;
////            }
////
////            Todo todo = new Todo();
////
////            todo.title = title;
////            todo.description = description;
////
////            db.todoDao().insert(todo);
////
////            Toast.makeText(this, "Todo Saved", Toast.LENGTH_SHORT).show();
////
////            finish();
////        });
////    }
////}
//
//
//package com.example.todoapp;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;
//
//public class AddTodoActivity extends AppCompatActivity {
//
//    private EditText etTitle, etDescription;
//    private Button btnSave;
//
//    private AppDatabase db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_todo);
//
//        etTitle = findViewById(R.id.etTitle);
//        etDescription = findViewById(R.id.etDescription);
//        btnSave = findViewById(R.id.btnSave);
//
//        db = Room.databaseBuilder(
//                        getApplicationContext(),
//                        AppDatabase.class,
//                        "todo_database"
//                )
//                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build();
//
//        btnSave.setOnClickListener(v -> saveTodo());
//    }
//
//    private void saveTodo() {
//
//        String title = etTitle.getText().toString().trim();
//        String description = etDescription.getText().toString().trim();
//
//        if (title.isEmpty()) {
//            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Todo todo = new Todo();
//        todo.title = title;
//        todo.description = description;
//        todo.dueDate = "";
//        todo.completed = false;
//
//        try {
//
//            db.todoDao().insert(todo);
//
//            Toast.makeText(this, "Todo Saved Successfully", Toast.LENGTH_SHORT).show();
//
//            finish();
//
//        } catch (Exception e) {
//
//            Toast.makeText(this,
//                    "Error : " + e.getMessage(),
//                    Toast.LENGTH_LONG).show();
//
//            e.printStackTrace();
//        }
//    }
//}


package com.example.todoapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddTodoActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private Button btnSave;

    private AppDatabase db;

    // Logged-in user ID
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);

        // Get userId from MainActivity
        userId = getIntent().getIntExtra("userId", -1);

        db = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "todo_database"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        btnSave.setOnClickListener(v -> saveTodo());
    }

    private void saveTodo() {

        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }

        Todo todo = new Todo();

        todo.title = title;
        todo.description = description;
        todo.dueDate = "";
        todo.completed = false;

        // Assign todo to logged-in user
        todo.userId = userId;

        try {

            db.todoDao().insert(todo);

            Toast.makeText(
                    this,
                    "Todo Saved Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Error : " + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();

            e.printStackTrace();
        }
    }
}
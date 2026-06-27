//////package com.example.todoapp;
//////
//////import android.os.Bundle;
//////
//////import androidx.activity.EdgeToEdge;
//////import androidx.appcompat.app.AppCompatActivity;
//////import androidx.core.graphics.Insets;
//////import androidx.core.view.ViewCompat;
//////import androidx.core.view.WindowInsetsCompat;
//////
//////public class AddTodoActivity extends AppCompatActivity {
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        EdgeToEdge.enable(this);
//////        setContentView(R.layout.activity_add_todo);
//////        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//////            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//////            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//////            return insets;
//////        });
//////    }
//////}
////
////
////
//////package com.example.todoapp;
//////
//////import android.os.Bundle;
//////import androidx.appcompat.app.AppCompatActivity;
//////
//////public class AddTodoActivity extends AppCompatActivity {
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_add_todo);
//////    }
//////}
////
//////package com.example.todoapp;
//////
//////import android.os.Bundle;
//////import android.widget.Button;
//////import android.widget.EditText;
//////import android.widget.Toast;
//////
//////import androidx.appcompat.app.AppCompatActivity;
//////import androidx.room.Room;
//////
//////public class AddTodoActivity extends AppCompatActivity {
//////
//////    EditText etTitle, etDescription;
//////    Button btnSave;
//////
//////    AppDatabase db;
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_add_todo);
//////
//////        etTitle = findViewById(R.id.etTitle);
//////        etDescription = findViewById(R.id.etDescription);
//////        btnSave = findViewById(R.id.btnSave);
//////
//////        db = Room.databaseBuilder(
//////                getApplicationContext(),
//////                AppDatabase.class,
//////                "todo_database"
//////        ).allowMainThreadQueries().build();
//////
//////        btnSave.setOnClickListener(v -> {
//////
//////            String title = etTitle.getText().toString().trim();
//////            String description = etDescription.getText().toString().trim();
//////
//////            if (title.isEmpty()) {
//////                Toast.makeText(this, "Enter a title", Toast.LENGTH_SHORT).show();
//////                return;
//////            }
//////
//////            Todo todo = new Todo();
//////
//////            todo.title = title;
//////            todo.description = description;
//////
//////            db.todoDao().insert(todo);
//////
//////            Toast.makeText(this, "Todo Saved", Toast.LENGTH_SHORT).show();
//////
//////            finish();
//////        });
//////    }
//////}
////
////
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
////    private EditText etTitle, etDescription;
////    private Button btnSave;
////
////    private AppDatabase db;
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
////                        getApplicationContext(),
////                        AppDatabase.class,
////                        "todo_database"
////                )
////                .fallbackToDestructiveMigration()
////                .allowMainThreadQueries()
////                .build();
////
////        btnSave.setOnClickListener(v -> saveTodo());
////    }
////
////    private void saveTodo() {
////
////        String title = etTitle.getText().toString().trim();
////        String description = etDescription.getText().toString().trim();
////
////        if (title.isEmpty()) {
////            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
////            return;
////        }
////
////        Todo todo = new Todo();
////        todo.title = title;
////        todo.description = description;
////        todo.dueDate = "";
////        todo.completed = false;
////
////        try {
////
////            db.todoDao().insert(todo);
////
////            Toast.makeText(this, "Todo Saved Successfully", Toast.LENGTH_SHORT).show();
////
////            finish();
////
////        } catch (Exception e) {
////
////            Toast.makeText(this,
////                    "Error : " + e.getMessage(),
////                    Toast.LENGTH_LONG).show();
////
////            e.printStackTrace();
////        }
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
//    // Logged-in user ID
//    private int userId;
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
//        // Get userId from MainActivity
//        userId = getIntent().getIntExtra("userId", -1);
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
//
//        todo.title = title;
//        todo.description = description;
//        todo.dueDate = "";
//        todo.completed = false;
//
//        // Assign todo to logged-in user
//        todo.userId = userId;
//
//        try {
//
//            db.todoDao().insert(todo);
//
//            Toast.makeText(
//                    this,
//                    "Todo Saved Successfully",
//                    Toast.LENGTH_SHORT
//            ).show();
//
//            finish();
//
//        } catch (Exception e) {
//
//            Toast.makeText(
//                    this,
//                    "Error : " + e.getMessage(),
//                    Toast.LENGTH_LONG
//            ).show();
//
//            e.printStackTrace();
//        }
//    }
//}


package com.example.todoapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTodoActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private EditText etDueDate;
    private EditText etTags;

    private Spinner spPriority;

    private Button btnSave;

    private AppDatabase db;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etDueDate = findViewById(R.id.etDueDate);
        etTags = findViewById(R.id.etTags);

        spPriority = findViewById(R.id.spPriority);

        btnSave = findViewById(R.id.btnSave);

        userId = getIntent().getIntExtra("userId", -1);

        db = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "todo_database"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        // Priority Spinner

        String[] priorities = {
                "High",
                "Medium",
                "Low"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        priorities
                );

        spPriority.setAdapter(adapter);

        // Date Picker

        etDueDate.setOnClickListener(v -> showDatePicker());

        btnSave.setOnClickListener(v -> saveTodo());

    }

    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog =
                new DatePickerDialog(
                        this,
                        (view, year, month, day) -> {

                            Calendar selected =
                                    Calendar.getInstance();

                            selected.set(year, month, day);

                            String date =
                                    new SimpleDateFormat(
                                            "dd/MM/yyyy",
                                            Locale.getDefault()
                                    ).format(selected.getTime());

                            etDueDate.setText(date);

                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

        dialog.show();

    }

    private void saveTodo() {

        String title = etTitle.getText().toString().trim();

        String description =
                etDescription.getText().toString().trim();

        String dueDate =
                etDueDate.getText().toString().trim();

        String tags =
                etTags.getText().toString().trim();

        String priority =
                spPriority.getSelectedItem().toString();

        if (title.isEmpty()) {

            Toast.makeText(
                    this,
                    "Enter Title",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        Todo todo = new Todo();

        todo.title = title;
        todo.description = description;
        todo.dueDate = dueDate;
        todo.tags = tags;
        todo.priority = priority;
        todo.completed = false;
        todo.userId = userId;

        todo.createdAt = System.currentTimeMillis();
        todo.updatedAt = System.currentTimeMillis();

        db.todoDao().insert(todo);

        Toast.makeText(
                this,
                "Todo Saved",
                Toast.LENGTH_SHORT
        ).show();

        finish();

    }

}
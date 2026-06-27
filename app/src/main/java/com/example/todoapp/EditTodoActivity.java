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
//////public class EditTodoActivity extends AppCompatActivity {
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        EdgeToEdge.enable(this);
//////        setContentView(R.layout.activity_edit_todo2);
//////        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//////            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//////            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//////            return insets;
//////        });
//////    }
//////}
////
////
////package com.example.todoapp;
////
////import android.os.Bundle;
////import androidx.appcompat.app.AppCompatActivity;
////
////public class EditTodoActivity extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_edit_todo);
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
//public class EditTodoActivity extends AppCompatActivity {
//
//    private EditText etTitle, etDescription;
//    private Button btnUpdate;
//
//    private AppDatabase db;
//
//    private Todo todo;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_todo);
//
//        etTitle = findViewById(R.id.etTitle);
//        etDescription = findViewById(R.id.etDescription);
//        btnUpdate = findViewById(R.id.btnUpdate);
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
//        int todoId = getIntent().getIntExtra("todoId", -1);
//
//        if (todoId == -1) {
//            Toast.makeText(this, "Invalid Todo", Toast.LENGTH_SHORT).show();
//            finish();
//            return;
//        }
//
//        todo = db.todoDao().getTodoById(todoId);
//
//        if (todo == null) {
//            Toast.makeText(this, "Todo not found", Toast.LENGTH_SHORT).show();
//            finish();
//            return;
//        }
//
//        etTitle.setText(todo.title);
//        etDescription.setText(todo.description);
//
//        btnUpdate.setOnClickListener(v -> {
//
//            String title = etTitle.getText().toString().trim();
//            String description = etDescription.getText().toString().trim();
//
//            if (title.isEmpty()) {
//                Toast.makeText(this, "Enter title", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            todo.title = title;
//            todo.description = description;
//
//            db.todoDao().update(todo);
//
//            Toast.makeText(this,
//                    "Todo Updated",
//                    Toast.LENGTH_SHORT).show();
//
//            finish();
//
//        });
//
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

public class EditTodoActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private EditText etDueDate;
    private EditText etTags;

    private Spinner spPriority;

    private Button btnUpdate;

    private AppDatabase db;

    private Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etDueDate = findViewById(R.id.etDueDate);
        etTags = findViewById(R.id.etTags);

        spPriority = findViewById(R.id.spPriority);

        btnUpdate = findViewById(R.id.btnUpdate);

        db = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "todo_database"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

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

        int todoId = getIntent().getIntExtra("todoId", -1);

        todo = db.todoDao().getTodoById(todoId);

        if (todo == null) {
            finish();
            return;
        }

        etTitle.setText(todo.title);
        etDescription.setText(todo.description);
        etDueDate.setText(todo.dueDate);
        etTags.setText(todo.tags);

        if (todo.priority != null) {

            switch (todo.priority) {

                case "High":
                    spPriority.setSelection(0);
                    break;

                case "Medium":
                    spPriority.setSelection(1);
                    break;

                case "Low":
                    spPriority.setSelection(2);
                    break;
            }

        }

        etDueDate.setOnClickListener(v -> showDatePicker());

        btnUpdate.setOnClickListener(v -> updateTodo());

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

    private void updateTodo() {

        todo.title = etTitle.getText().toString().trim();
        todo.description = etDescription.getText().toString().trim();
        todo.dueDate = etDueDate.getText().toString().trim();
        todo.tags = etTags.getText().toString().trim();
        todo.priority = spPriority.getSelectedItem().toString();

        todo.updatedAt = System.currentTimeMillis();

        db.todoDao().update(todo);

        Toast.makeText(this,
                "Todo Updated",
                Toast.LENGTH_SHORT).show();

        finish();

    }

}
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
////public class EditTodoActivity extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        EdgeToEdge.enable(this);
////        setContentView(R.layout.activity_edit_todo2);
////        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
////            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
////            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
////            return insets;
////        });
////    }
////}
//
//
//package com.example.todoapp;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class EditTodoActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_todo);
//    }
//}


package com.example.todoapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class EditTodoActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private Button btnUpdate;

    private AppDatabase db;

    private Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnUpdate = findViewById(R.id.btnUpdate);

        db = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "todo_database"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        int todoId = getIntent().getIntExtra("todoId", -1);

        if (todoId == -1) {
            Toast.makeText(this, "Invalid Todo", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        todo = db.todoDao().getTodoById(todoId);

        if (todo == null) {
            Toast.makeText(this, "Todo not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        etTitle.setText(todo.title);
        etDescription.setText(todo.description);

        btnUpdate.setOnClickListener(v -> {

            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(this, "Enter title", Toast.LENGTH_SHORT).show();
                return;
            }

            todo.title = title;
            todo.description = description;

            db.todoDao().update(todo);

            Toast.makeText(this,
                    "Todo Updated",
                    Toast.LENGTH_SHORT).show();

            finish();

        });

    }
}
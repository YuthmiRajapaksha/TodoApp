////////package com.example.todoapp;
////////
////////import android.os.Bundle;
////////
////////import androidx.activity.EdgeToEdge;
////////import androidx.appcompat.app.AppCompatActivity;
////////import androidx.core.graphics.Insets;
////////import androidx.core.view.ViewCompat;
////////import androidx.core.view.WindowInsetsCompat;
////////import android.content.Intent;
////////import android.widget.Button;
////////
////////public class MainActivity extends AppCompatActivity {
////////
////////    @Override
////////    protected void onCreate(Bundle savedInstanceState) {
////////        super.onCreate(savedInstanceState);
////////        EdgeToEdge.enable(this);
////////        setContentView(R.layout.activity_main);
////////
////////        Button btnAddTodo = findViewById(R.id.btnAddTodo);
////////
////////        btnAddTodo.setOnClickListener(v -> {
////////
////////            Intent intent =
////////                    new Intent(MainActivity.this,
////////                            AddTodoActivity.class);
////////
////////            startActivity(intent);
////////
////////        });
////////
////////        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
////////            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
////////            v.setPadding(systemBars.left, systemBars.top,
////////                    systemBars.right, systemBars.bottom);
////////            return insets;
////////        });
////////    }
////////}
//////
//////
////////package com.example.todoapp;
////////
////////import android.content.Intent;
////////import android.os.Bundle;
////////import android.widget.Button;
////////import android.widget.Toast;
////////
////////import androidx.appcompat.app.AppCompatActivity;
////////
////////public class MainActivity extends AppCompatActivity {
////////
////////    Button btnAddTodo;
////////
////////    @Override
////////    protected void onCreate(Bundle savedInstanceState) {
////////        super.onCreate(savedInstanceState);
////////        setContentView(R.layout.activity_main);
////////
////////        btnAddTodo = findViewById(R.id.btnAddTodo);
////////
////////        btnAddTodo.setOnClickListener(v -> {
////////
////////            Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
////////            startActivity(intent);
////////
////////        });
////////    }
////////}
//////
////////package com.example.todoapp;
////////
////////import android.content.Intent;
////////import android.os.Bundle;
////////import android.widget.Button;
////////import android.widget.Toast;
////////
////////import androidx.appcompat.app.AppCompatActivity;
////////
////////public class MainActivity extends AppCompatActivity {
////////
////////    Button btnAddTodo;
////////
////////    @Override
////////    protected void onCreate(Bundle savedInstanceState) {
////////        super.onCreate(savedInstanceState);
////////
////////        Toast.makeText(this, "MainActivity Loaded", Toast.LENGTH_LONG).show();
////////
////////        setContentView(R.layout.activity_main);
////////
////////        btnAddTodo = findViewById(R.id.btnAddTodo);
////////
////////        if (btnAddTodo == null) {
////////            Toast.makeText(this, "btnAddTodo NOT FOUND", Toast.LENGTH_LONG).show();
////////            return;
////////        }
////////
////////        btnAddTodo.setOnClickListener(v ->
////////                startActivity(new Intent(MainActivity.this, AddTodoActivity.class)));
////////    }
////////}
//////
//////
//////package com.example.todoapp;
//////
//////import android.content.Intent;
//////import android.os.Bundle;
//////
//////import android.widget.Button;
//////
//////import androidx.recyclerview.widget.LinearLayoutManager;
//////import androidx.recyclerview.widget.RecyclerView;
//////
//////
//////import androidx.appcompat.app.AppCompatActivity;
//////import androidx.room.Room;
//////
//////import java.util.ArrayList;
//////import java.util.List;
//////
//////public class MainActivity extends AppCompatActivity {
//////
//////    Button btnAddTodo;
//////    RecyclerView recyclerTodos;
//////
//////    AppDatabase db;
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_main);
//////
//////        btnAddTodo = findViewById(R.id.btnAddTodo);
//////        recyclerTodos = findViewById(R.id.recyclerTodos);
//////        recyclerTodos.setLayoutManager(new LinearLayoutManager(this));
//////
//////        db = Room.databaseBuilder(
//////                getApplicationContext(),
//////                AppDatabase.class,
//////                "todo_database"
//////        ).allowMainThreadQueries().build();
//////
//////        btnAddTodo.setOnClickListener(v ->
//////                startActivity(new Intent(MainActivity.this, AddTodoActivity.class))
//////        );
//////    }
//////
//////    @Override
//////    protected void onResume() {
//////        super.onResume();
//////        loadTodos();
//////    }
//////
//////    private void loadTodos() {
//////
//////        List<Todo> todos = db.todoDao().getAllTodos();
//////
//////        List<String> todoList = new ArrayList<>();
//////
//////        for (Todo todo : todos) {
//////            todoList.add(todo.title + "\n" + todo.description);
//////        }
//////
//////
//////    }
//////}
////
////
////
////package com.example.todoapp;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.widget.Button;
////
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////import androidx.room.Room;
////
////import com.example.todoapp.adapter.TodoAdapter;
////
////import java.util.List;
////
////public class MainActivity extends AppCompatActivity {
////
////    Button btnAddTodo;
////    RecyclerView recyclerTodos;
////
////    AppDatabase db;
////    TodoAdapter adapter;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////
////        btnAddTodo = findViewById(R.id.btnAddTodo);
////        recyclerTodos = findViewById(R.id.recyclerTodos);
////
////        db = Room.databaseBuilder(
////                        getApplicationContext(),
////                        AppDatabase.class,
////                        "todo_database"
////                )
////                .allowMainThreadQueries()
////                .build();
////
////        recyclerTodos.setLayoutManager(new LinearLayoutManager(this));
////
////        btnAddTodo.setOnClickListener(v ->
////                startActivity(new Intent(MainActivity.this, AddTodoActivity.class))
////        );
////    }
////
////    @Override
////    protected void onResume() {
////        super.onResume();
////        loadTodos();
////    }
////
////    private void loadTodos() {
////
////        List<Todo> todoList = db.todoDao().getAllTodos();
////
////        adapter = new TodoAdapter(
////                this,
////                todoList,
////                db
////        );
////
////        recyclerTodos.setAdapter(adapter);
////    }
////}
//
//
//
//package com.example.todoapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;
//
//import com.example.todoapp.adapter.TodoAdapter;
//
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    private Button btnAddTodo;
//
//    private RecyclerView recyclerTodos;
//
//    private TextView txtUsername;
//    private TextView txtTotal;
//
//    private AppDatabase db;
//
//    private TodoAdapter adapter;
//
//    private int userId;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btnAddTodo = findViewById(R.id.btnAddTodo);
//        recyclerTodos = findViewById(R.id.recyclerTodos);
//
//        txtUsername = findViewById(R.id.txtUsername);
//        txtTotal = findViewById(R.id.txtTotal);
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
//        recyclerTodos.setLayoutManager(
//                new LinearLayoutManager(this)
//        );
//
//        userId = getIntent().getIntExtra("userId", -1);
//
//        String username = getIntent().getStringExtra("username");
//
//        if (username != null) {
//            txtUsername.setText("Welcome, " + username);
//        }
//
//        String username =
//                getIntent().getStringExtra("username");
//
//        if (username != null) {
//            txtUsername.setText("Welcome, " + username);
//        }
//
//        btnAddTodo.setOnClickListener(v -> {
//
//            Intent intent =
//                    new Intent(
//                            MainActivity.this,
//                            AddTodoActivity.class
//                    );
//
//            startActivity(intent);
//
//        });
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadTodos();
//    }
//
//    private void loadTodos() {
//
//        List<Todo> todoList =
//                db.todoDao().getAllTodos();
//
//        txtTotal.setText("Total Todos : " + todoList.size());
//
//        adapter =
//                new TodoAdapter(
//                        this,
//                        todoList,
//                        db
//                );
//
//        recyclerTodos.setAdapter(adapter);
//
//    }
//
//}


//package com.example.todoapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;
//
//import com.example.todoapp.adapter.TodoAdapter;
//
//import java.util.List;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//
//public class MainActivity extends AppCompatActivity {
//
//
//    private FloatingActionButton fabAdd;
//    private RecyclerView recyclerTodos;
//
//    private TextView txtUsername;
//    private TextView txtTotal;
//
//    private AppDatabase db;
//    private TodoAdapter adapter;
//
//    // Logged in user
//    private int userId;
//    private String username;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        fabAdd = findViewById(R.id.fabAdd);
//        recyclerTodos = findViewById(R.id.recyclerTodos);
//        txtUsername = findViewById(R.id.txtUsername);
//        txtTotal = findViewById(R.id.txtTotal);
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
//        recyclerTodos.setLayoutManager(new LinearLayoutManager(this));
//
//        // Get logged-in user information
//        userId = getIntent().getIntExtra("userId", -1);
//        username = getIntent().getStringExtra("username");
//
//        if (username != null) {
//            txtUsername.setText("Welcome, " + username);
//        }
//
//        fabAdd.setOnClickListener(v -> {
//
//            Intent intent = new Intent(
//                    MainActivity.this,
//                    AddTodoActivity.class
//            );
//
//            // Pass logged-in user id
//            intent.putExtra("userId", userId);
//
//            startActivity(intent);
//
//        });
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadTodos();
//    }
//
//    private void loadTodos() {
//
//        List<Todo> todoList =
//                db.todoDao().getTodosByUser(userId);
//
//        txtTotal.setText("Total Todos : " + todoList.size());
//
//        adapter = new TodoAdapter(
//                this,
//                todoList,
//                db
//        );
//
//        recyclerTodos.setAdapter(adapter);
//
//    }
//}





package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.todoapp.adapter.TodoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAdd;
    private RecyclerView recyclerTodos;

    private TextView txtUsername;
    private TextView txtTotal;
    private TextView txtCompleted;
    private TextView txtPending;

    private EditText etSearch;

    private AppDatabase db;
    private TodoAdapter adapter;

    private int userId;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAdd = findViewById(R.id.fabAdd);
        recyclerTodos = findViewById(R.id.recyclerTodos);

        txtUsername = findViewById(R.id.txtUsername);
        txtTotal = findViewById(R.id.txtTotal);
        txtCompleted = findViewById(R.id.txtCompleted);
        txtPending = findViewById(R.id.txtPending);

        etSearch = findViewById(R.id.etSearch);

        db = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "todo_database"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        recyclerTodos.setLayoutManager(new LinearLayoutManager(this));

        // Get logged-in user
        userId = getIntent().getIntExtra("userId", -1);
        username = getIntent().getStringExtra("username");

        if (username != null) {
            txtUsername.setText("Welcome, " + username + " 👋");
        }

        fabAdd.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    AddTodoActivity.class
            );

            intent.putExtra("userId", userId);

            startActivity(intent);

        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                // Search feature will be added next
//
//            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<Todo> filtered =
                        db.todoDao().searchTodos(
                                userId,
                                s.toString()
                        );

                adapter = new TodoAdapter(
                        MainActivity.this,
                        filtered,
                        db
                );

                recyclerTodos.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTodos();
    }

//    private void loadTodos() {
//
//        List<Todo> todoList = db.todoDao().getTodosByUser(userId);
//
//        int completed = 0;
//
//        for (Todo todo : todoList) {
//
//            if (todo.completed) {
//                completed++;
//            }
//
//        }
//
//        int pending = todoList.size() - completed;
//
//        txtTotal.setText(String.valueOf(todoList.size()));
//        txtCompleted.setText(String.valueOf(completed));
//        txtPending.setText(String.valueOf(pending));
//
//        adapter = new TodoAdapter(
//                this,
//                todoList,
//                db
//        );
//
//        recyclerTodos.setAdapter(adapter);
//
//    }
//

    private void loadTodos() {

        List<Todo> todoList = db.todoDao().getTodosByUser(userId);

        int completed = 0;

        for (Todo todo : todoList) {

            if (todo.completed) {
                completed++;
            }

        }

        int pending = todoList.size() - completed;

        txtTotal.setText(String.valueOf(todoList.size()));
        txtCompleted.setText(String.valueOf(completed));
        txtPending.setText(String.valueOf(pending));

        adapter = new TodoAdapter(
                this,
                todoList,
                db
        );

        recyclerTodos.setAdapter(adapter);

    }

}
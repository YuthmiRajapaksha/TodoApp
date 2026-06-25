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
//////public class LoginActivity extends AppCompatActivity {
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        EdgeToEdge.enable(this);
//////        setContentView(R.layout.activity_login);
//////        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//////            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//////            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//////            return insets;
//////        });
//////    }
//////}
////
////
//////package com.example.todoapp;
//////
//////import android.os.Bundle;
//////import androidx.appcompat.app.AppCompatActivity;
//////
//////public class LoginActivity extends AppCompatActivity {
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_login);
//////    }
//////}
////
////package com.example.todoapp;
////
////import android.content.Intent;
////import android.content.SharedPreferences;
////import android.os.Bundle;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.Toast;
////
////import androidx.appcompat.app.AppCompatActivity;
////
////public class LoginActivity extends AppCompatActivity {
////
////    EditText etUsername, etPassword;
////    Button btnLogin, btnRegister;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_login);
////
////        etUsername = findViewById(R.id.etUsername);
////        etPassword = findViewById(R.id.etPassword);
////
////        btnLogin = findViewById(R.id.btnLogin);
////        btnRegister = findViewById(R.id.btnRegister);
////
////        btnLogin.setOnClickListener(v -> {
////
////            String username = etUsername.getText().toString();
////            String password = etPassword.getText().toString();
////
////            SharedPreferences prefs =
////                    getSharedPreferences("UserData", MODE_PRIVATE);
////
////            String savedPassword =
////                    prefs.getString(username, "");
////
////            if (savedPassword.equals(password)) {
////
////                Toast.makeText(this,
////                        "Login Successful",
////                        Toast.LENGTH_SHORT).show();
////
////                startActivity(
////                        new Intent(this, MainActivity.class));
////
////            } else {
////
////                Toast.makeText(this,
////                        "Invalid Username or Password",
////                        Toast.LENGTH_SHORT).show();
////            }
////        });
////
////        btnRegister.setOnClickListener(v -> {
////            startActivity(
////                    new Intent(this, RegisterActivity.class));
////        });
////    }
////}
//
//package com.example.todoapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;
//
//public class LoginActivity extends AppCompatActivity {
//
//    EditText etUsername, etPassword;
//    Button btnLogin, btnRegister;
//
//    AppDatabase db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        etUsername = findViewById(R.id.etUsername);
//        etPassword = findViewById(R.id.etPassword);
//
//        btnLogin = findViewById(R.id.btnLogin);
//        btnRegister = findViewById(R.id.btnRegister);
//
//        // Initialize Room Database
//        db = Room.databaseBuilder(
//                        getApplicationContext(),
//                        AppDatabase.class,
//                        "todo_database"
//                )
//                .allowMainThreadQueries()
//                .build();
//
//        // Login Button
//        btnLogin.setOnClickListener(v -> {
//
//            String username =
//                    etUsername.getText().toString().trim();
//
//            String password =
//                    PasswordUtils.hashPassword(
//                            etPassword.getText().toString().trim()
//                    );
//
//            User user =
//                    db.userDao().login(username, password);
//
//            if (user != null) {
//
//                Toast.makeText(
//                        this,
//                        "Login Successful",
//                        Toast.LENGTH_SHORT
//                ).show();
//
//                startActivity(
//                        new Intent(
//                                LoginActivity.this,
//                                MainActivity.class
//                        )
//                );
//
//                finish();
//
//            } else {
//
//                Toast.makeText(
//                        this,
//                        "Invalid Username or Password",
//                        Toast.LENGTH_SHORT
//                ).show();
//            }
//        });
//
//        // Open Register Screen
//        btnRegister.setOnClickListener(v -> {
//
////            startActivity(
////                    new Intent(
////                            LoginActivity.this,
////                            RegisterActivity.class
////                    )
////            );
//
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            intent.putExtra("username", user.username);
//            startActivity(intent);
//        });
//    }
//}


package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Initialize Room Database
        db = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "todo_database"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        // Login Button
        btnLogin.setOnClickListener(v -> {

            String username = etUsername.getText().toString().trim();

            String password = PasswordUtils.hashPassword(
                    etPassword.getText().toString().trim()
            );

            User user = db.userDao().login(username, password);

            if (user != null) {

                Toast.makeText(
                        LoginActivity.this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

//                Intent intent = new Intent(
//                        LoginActivity.this,
//                        MainActivity.class
//                );
//
//                // Pass username to MainActivity
//                intent.putExtra("username", user.username);
//
//                startActivity(intent);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                intent.putExtra("userId", user.id);
                intent.putExtra("username", user.username);

                startActivity(intent);

                finish();

            } else {

                Toast.makeText(
                        LoginActivity.this,
                        "Invalid Username or Password",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        // Register Button
        btnRegister.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );

            startActivity(intent);

        });

    }
}

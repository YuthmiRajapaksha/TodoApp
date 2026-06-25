////package com.example.todoapp;
////
////import android.os.Bundle;
////import androidx.appcompat.app.AppCompatActivity;
////
////public class RegisterActivity extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_register);
////    }
////}
//
//package com.example.todoapp;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    EditText etUsername, etPassword, etConfirmPassword;
//    Button btnRegister;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        etUsername = findViewById(R.id.etUsername);
//        etPassword = findViewById(R.id.etPassword);
//        etConfirmPassword = findViewById(R.id.etConfirmPassword);
//        btnRegister = findViewById(R.id.btnRegister);
//
//        btnRegister.setOnClickListener(v -> {
//
//            String username = etUsername.getText().toString();
//            String password = etPassword.getText().toString();
//            String confirm = etConfirmPassword.getText().toString();
//
//            if (!password.equals(confirm)) {
//                Toast.makeText(this,
//                        "Passwords do not match",
//                        Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            SharedPreferences prefs =
//                    getSharedPreferences("UserData", MODE_PRIVATE);
//
//            prefs.edit()
//                    .putString(username, password)
//                    .apply();
//
//            Toast.makeText(this,
//                    "Registration Successful",
//                    Toast.LENGTH_SHORT).show();
//
//            finish();
//        });
//    }
//}


package com.example.todoapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword, etConfirmPassword;
    Button btnRegister;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        db = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "todo_database"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        btnRegister.setOnClickListener(v -> {

            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirm = etConfirmPassword.getText().toString().trim();

            if(username.isEmpty() ||
                    password.isEmpty() ||
                    confirm.isEmpty()) {

                Toast.makeText(this,
                        "Fill all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if(!password.equals(confirm)) {

                Toast.makeText(this,
                        "Passwords do not match",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            User existing =
                    db.userDao().getUserByUsername(username);

            if(existing != null){

                Toast.makeText(this,
                        "Username already exists",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User();
            user.username = username;
            user.password =
                    PasswordUtils.hashPassword(password);

            db.userDao().insert(user);

            Toast.makeText(this,
                    "Registration Successful",
                    Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}
package com.example.beluxe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DatabaseHelper db = new DatabaseHelper(getBaseContext());

        //inserting data into table login for auth
        db.insertLogin("belia", "asd");
        db.insertLogin("admin", "admin");

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Silahkan Masukan Nama dan Kata Sandi", Toast.LENGTH_SHORT).show();
                } else {
                    // Lakukan logika autentikasi Anda di sini
                    // Misalnya, memeriksa apakah username dan password valid

                    // Check if the username and password are correct
                    boolean isAuthenticated = db.checkLogin(username, password);

                    if (isAuthenticated) {
                        // Redirect the user to the main activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Selamat Datang " + username, Toast.LENGTH_SHORT).show();
                    } else {
                        // Show an error message
                        Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
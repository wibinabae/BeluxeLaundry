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

                    if (username.equals("belia") && password.equals("asd")) {
                        // Autentikasi berhasil, lakukan tindakan selanjutnya
                        Toast.makeText(LoginActivity.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        // Autentikasi gagal, tampilkan pesan kesalahan
                        Toast.makeText(LoginActivity.this, "Nama dan Kata Sandi Salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
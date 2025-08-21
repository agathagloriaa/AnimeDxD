package com.example.animedxd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvErrorUsername, tvErrorPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvErrorUsername = findViewById(R.id.tvErrorUsername);
        tvErrorPassword = findViewById(R.id.tvErrorPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        boolean isValid = true;


        if (TextUtils.isEmpty(username)) {
            tvErrorUsername.setText("Username must be filled in");
            tvErrorUsername.setVisibility(View.VISIBLE);
            isValid = false;
        } else if (username.length() < 5 || username.length() > 10) {
            tvErrorUsername.setText("Username must be 5-10 characters");
            tvErrorUsername.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            tvErrorUsername.setVisibility(View.INVISIBLE);
        }


        if (TextUtils.isEmpty(password)) {
            tvErrorPassword.setText("Password must be filled in");
            tvErrorPassword.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            tvErrorPassword.setVisibility(View.INVISIBLE);
        }

        if (!isValid) return;

        if (isValid) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("username_key", username);
            startActivity(intent);
            finish();
        }
    }

}

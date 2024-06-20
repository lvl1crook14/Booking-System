package com.example.greenleafhotelapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText etAdminUsername, etAdminPassword;
    private Button btnAdminLogin;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etAdminUsername = findViewById(R.id.et_admin_username);
        etAdminPassword = findViewById(R.id.et_admin_password);
        btnAdminLogin = findViewById(R.id.btn_admin_login);

        db = new Database(this);

        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etAdminUsername.getText().toString();
                String password = etAdminPassword.getText().toString();
                // Simple authentication logic
                if (username.equals("admin") && password.equals("password")) {
                    Intent intent = new Intent(AdminLoginActivity.this, ViewDataActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AdminLoginActivity.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

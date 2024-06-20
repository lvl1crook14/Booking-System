package com.example.greenleafhotelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText etAdminUsername, etAdminPassword;
    private Button btnAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etAdminUsername = findViewById(R.id.et_admin_username);
        etAdminPassword = findViewById(R.id.et_admin_password);
        btnAdminLogin = findViewById(R.id.btn_admin_login);

        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etAdminUsername.getText().toString().trim();
                String password = etAdminPassword.getText().toString().trim();

                // Replace with actual admin credentials check
                if (username.equals("admin") && password.equals("admin123")) {
                    Intent intent = new Intent(AdminLoginActivity.this, ViewDataActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AdminLoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

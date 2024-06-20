package com.example.greenleafhotelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnAdminLogin, btnViewData, btn1, btn2, btn3, btn4, btn5, btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdminLogin = findViewById(R.id.btn_admin_login);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);

        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        View.OnClickListener bookClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, booklog.class);
                startActivity(intent);
            }
        };

        btn1.setOnClickListener(bookClickListener);
        btn2.setOnClickListener(bookClickListener);
        btn3.setOnClickListener(bookClickListener);
        btn4.setOnClickListener(bookClickListener);
        btn5.setOnClickListener(bookClickListener);
        btn6.setOnClickListener(bookClickListener);
    }
}
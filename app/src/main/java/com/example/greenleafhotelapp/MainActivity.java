package com.example.greenleafhotelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnAdminLogin, btn1, btn2, btn3, btn4, btn5, btn6;

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

        // OnClickListener for booking buttons
        View.OnClickListener bookClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, booklog.class);  // Assuming the class name is BookLog

                // Pass information about the room type based on the button clicked
                int id = v.getId();
                if (id == R.id.btn1) {
                    intent.putExtra("roomType", "Deluxe Twin Room");
                } else if (id == R.id.btn2) {
                    intent.putExtra("roomType", "Deluxe King Room");
                } else if (id == R.id.btn3) {
                    intent.putExtra("roomType", "Executive Room");
                } else if (id == R.id.btn4) {
                    intent.putExtra("roomType", "One-Bedroom Suite");
                } else if (id == R.id.btn5) {
                    intent.putExtra("roomType", "Veranda Suite");
                } else if (id == R.id.btn6) {
                    intent.putExtra("roomType", "Garden Suite");
                } else {
                    // Handle unexpected case
                }
                startActivity(intent);
            }
        };

        // Assign the same OnClickListener to all booking buttons
        btn1.setOnClickListener(bookClickListener);
        btn2.setOnClickListener(bookClickListener);
        btn3.setOnClickListener(bookClickListener);
        btn4.setOnClickListener(bookClickListener);
        btn5.setOnClickListener(bookClickListener);
        btn6.setOnClickListener(bookClickListener);
    }
}

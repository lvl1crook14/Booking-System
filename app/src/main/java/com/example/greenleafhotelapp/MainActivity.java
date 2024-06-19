package com.example.greenleafhotelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button =(Button) findViewById(R.id.btn1);
        Button button2 =(Button) findViewById(R.id.btn2);
        Button button3 =(Button) findViewById(R.id.btn3);
        Button button4 =(Button) findViewById(R.id.btn4);
        Button button5 =(Button) findViewById(R.id.btn5);
        Button button6 =(Button) findViewById(R.id.btn6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,booklog.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,booklog.class);
                startActivity(intent);
            }
        });
    }
}
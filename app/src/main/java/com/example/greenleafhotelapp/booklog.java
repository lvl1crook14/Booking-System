package com.example.greenleafhotelapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class booklog extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button datebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booklog);
        initDatePicker();
        datebutton = findViewById(R.id.date);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month = month + 1;
                String date = makeDateString(month, day, year);
            }
        };
    }

    private String makeDateString(int month, int day, int year)
    {
        return month + "" + day + "" + year;
    }

    public void openDatePicker(View view){


    }
}
package com.example.greenleafhotelapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

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
        datebutton.setText(getTodaysDate());
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month  = cal.get(Calendar.MONTH);
        month = month + 1;
        int day  = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month = month + 1;
                String date = makeDateString(month, day, year);
                datebutton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month  = cal.get(Calendar.MONTH);
        int day  = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
    }

    private String makeDateString(int month, int day, int year)
    {
        return getMonthFormat(month) + "" + day + "" + year;
    }

    private String getMonthFormat(int month)
    {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "COT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        return "JAN";
    }


    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}
package com.example.greenleafhotelapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserInputActivity extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextContactNumber, editTextEmail, editTextDateIn, editTextDateOut;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookliog);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextContactNumber = findViewById(R.id.editTextContactNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextDateIn = findViewById(R.id.editTextDateIn);
        editTextDateOut = findViewById(R.id.editTextDateOut);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Handle date pickers
        editTextDateIn.setOnClickListener(v -> showDatePickerDialog(editTextDateIn));
        editTextDateOut.setOnClickListener(v -> showDatePickerDialog(editTextDateOut));

        // Handle form submission
        btnSubmit.setOnClickListener(v -> submitForm());
    }

    private void showDatePickerDialog(EditText editText) {
        // Implement date picker dialog
    }

    private void submitForm() {
        // Handle form submission
    }
}

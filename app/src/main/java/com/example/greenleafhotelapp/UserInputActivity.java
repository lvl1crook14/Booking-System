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

        editTextFirstName = findViewById(R.id.et_first_name);
        editTextLastName = findViewById(R.id.et_last_name);
        editTextContactNumber = findViewById(R.id.et_contact_number);
        editTextEmail = findViewById(R.id.et_email);
        editTextDateIn = findViewById(R.id.et_date_in);
        editTextDateOut = findViewById(R.id.et_date_out);
        btnSubmit = findViewById(R.id.btn_submit);

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

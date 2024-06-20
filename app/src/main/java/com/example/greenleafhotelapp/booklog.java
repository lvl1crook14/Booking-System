package com.example.greenleafhotelapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class booklog extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextContactNumber, editTextEmail, editTextDateIn, editTextDateOut;
    private Button btnSubmit;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklog);

        dbHelper = new Database(this);

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
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String contactNumber = editTextContactNumber.getText().toString();
        String email = editTextEmail.getText().toString();
        String dateIn = editTextDateIn.getText().toString();
        String dateOut = editTextDateOut.getText().toString();

        // Insert guest details into the database
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues guestValues = new ContentValues();
        guestValues.put("guest_firstname", firstName);
        guestValues.put("guest_lastname", lastName);
        guestValues.put("guest_contact_number", contactNumber);
        guestValues.put("guest_email", email);
        long guestId = db.insert("Guest", null, guestValues);

        if (guestId != -1) {
            // Insert reservation details into the database
            ContentValues reservationValues = new ContentValues();
            reservationValues.put("guest_id", guestId);
            reservationValues.put("room_id", 1); // Assuming room_id is 1 for simplicity
            reservationValues.put("date_in", dateIn);
            reservationValues.put("date_out", dateOut);
            long reservationId = db.insert("Reservation", null, reservationValues);

            if (reservationId != -1) {
                Toast.makeText(this, "Booking successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to book reservation", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to add guest details", Toast.LENGTH_SHORT).show();
        }
    }
}

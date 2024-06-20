package com.example.greenleafhotelapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class booklog extends AppCompatActivity {

    private EditText etFirstName, etLastName, etContactNumber, etEmail, etDateIn, etDateOut;
    private Button btnSubmit;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklog);

        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etContactNumber = findViewById(R.id.et_contact_number);
        etEmail = findViewById(R.id.et_email);
        etDateIn = findViewById(R.id.et_date_in);
        etDateOut = findViewById(R.id.et_date_out);
        btnSubmit = findViewById(R.id.btn_submit);

        db = new Database(this);

        // Get room type from Intent
        String roomType = getIntent().getStringExtra("roomType");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String contactNumber = etContactNumber.getText().toString();
                String email = etEmail.getText().toString();
                String dateIn = etDateIn.getText().toString();
                String dateOut = etDateOut.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || contactNumber.isEmpty() || email.isEmpty() || dateIn.isEmpty() || dateOut.isEmpty()) {
                    Toast.makeText(booklog.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int roomId = getRoomId(roomType);  // Method to get room ID based on room type
                if (roomId == -1) {
                    Toast.makeText(booklog.this, "Invalid room type", Toast.LENGTH_SHORT).show();
                    return;
                }

                long reservationId = db.addSimpleRoomReservation(firstName, lastName, Integer.parseInt(contactNumber), email, roomId, dateIn, dateOut);

                if (reservationId == -1) {
                    Toast.makeText(booklog.this, "Failed to make reservation", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(booklog.this, "Reservation successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getRoomId(String roomType) {
        // Implement logic to map room types to room IDs
        // This could involve querying the database or using predefined values
        switch (roomType) {
            case "Deluxe Twin Room":
                return 1;
            case "Deluxe King Room":
                return 2;
            case "Executive Room":
                return 3;
            case "One-Bedroom Suite":
                return 4;
            case "Veranda Suite":
                return 5;
            case "Garden Suite":
                return 6;
            default:
                return -1;  // Return -1 or handle appropriately if the room type is unknown
        }
    }
}

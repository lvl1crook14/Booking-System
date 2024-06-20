package com.example.greenleafhotelapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

                // Insert guest data into database
                SQLiteDatabase database = db.getWritableDatabase();
                database.beginTransaction();
                try {
                    ContentValues guestValues = new ContentValues();
                    guestValues.put("guest_firstname", firstName);
                    guestValues.put("guest_lastname", lastName);
                    guestValues.put("guest_contact_number", contactNumber);
                    guestValues.put("guest_email", email);
                    long guestId = database.insert("Guest", null, guestValues);

                    if (guestId == -1) {
                        Toast.makeText(booklog.this, "Failed to insert guest data", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Assume room_id 1 for now; you can modify this to select a specific room
                    int roomId = 1;

                    ContentValues reservationValues = new ContentValues();
                    reservationValues.put("guest_id", guestId);
                    reservationValues.put("room_id", roomId);
                    reservationValues.put("date_in", dateIn);
                    reservationValues.put("date_out", dateOut);
                    long reservationId = database.insert("Reservation", null, reservationValues);

                    if (reservationId == -1) {
                        Toast.makeText(booklog.this, "Failed to insert reservation data", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    database.setTransactionSuccessful();
                    Toast.makeText(booklog.this, "Reservation successful", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(booklog.this, "Error occurred during reservation", Toast.LENGTH_SHORT).show();
                } finally {
                    database.endTransaction();
                }
            }
        });
    }
}

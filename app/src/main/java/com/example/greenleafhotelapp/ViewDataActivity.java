package com.example.greenleafhotelapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        ListView listView = findViewById(R.id.list_view); // Converted to local variable
        Database db = new Database(this); // Converted to local variable

        try {
            // Fetch guest and reservation data
            Cursor guestReservationCursor = db.getGuestReservationData();
            Cursor allGuestsCursor = db.getAllGuests();

            if (guestReservationCursor != null && allGuestsCursor != null) {
                Log.d("ViewDataActivity", "GuestReservationCursor count: " + guestReservationCursor.getCount());
                Log.d("ViewDataActivity", "AllGuestsCursor count: " + allGuestsCursor.getCount());

                if (guestReservationCursor.moveToFirst() && allGuestsCursor.moveToFirst()) {
                    Log.d("ViewDataActivity", "GuestReservationCursor and AllGuestsCursor have data");
                    GuestReservationAdapter adapter = new GuestReservationAdapter(this, guestReservationCursor, 0);
                    listView.setAdapter(adapter);
                } else {
                    Log.d("ViewDataActivity", "GuestReservationCursor or AllGuestsCursor is empty");
                    Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.d("ViewDataActivity", "GuestReservationCursor or AllGuestsCursor is null");
                Toast.makeText(this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("ViewDataActivity", "Error retrieving data: " + e.getMessage(), e);
            Toast.makeText(this, "Error retrieving data: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

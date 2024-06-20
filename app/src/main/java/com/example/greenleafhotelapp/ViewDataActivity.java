package com.example.greenleafhotelapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDataActivity extends AppCompatActivity {

    private ListView listView;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        listView = findViewById(R.id.list_view);
        db = new Database(this);

        // Fetch guest and reservation data
        Cursor cursor = db.getGuestReservationData();

        // Display data using SimpleCursorAdapter
        String[] from = {
                "guest_firstname",
                "guest_lastname",
                "guest_contact_number",
                "guest_email",
                "date_in",
                "date_out"
        };
        int[] to = {
                android.R.id.text1,
                android.R.id.text2,
                android.R.id.text1,
                android.R.id.text2,
                android.R.id.text1,
                android.R.id.text2
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_2, cursor, from, to, 0);
        listView.setAdapter(adapter);
    }
}

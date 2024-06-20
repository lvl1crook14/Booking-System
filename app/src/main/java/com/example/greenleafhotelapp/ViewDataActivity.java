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

        loadData();
    }

    private void loadData() {
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM Guest", null);

        String[] from = {
                "guest_firstname",
                "guest_lastname",
                "guest_contact_number",
                "guest_email"
        };
        int[] to = {
                R.id.tv_first_name,
                R.id.tv_last_name,
                R.id.tv_contact_number,
                R.id.tv_email
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_list_item, cursor, from, to, 0);
        listView.setAdapter(adapter);
    }
}

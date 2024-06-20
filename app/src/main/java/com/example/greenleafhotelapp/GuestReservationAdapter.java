package com.example.greenleafhotelapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class GuestReservationAdapter extends SimpleCursorAdapter {

    public GuestReservationAdapter(Context context, Cursor c, int flags) {
        super(context, R.layout.activity_list_item, c, new String[]{}, new int[]{}, flags);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_data, parent, false);
        }

        Cursor cursor = getCursor();
        cursor.moveToPosition(position);

        TextView firstName = convertView.findViewById(R.id.tv_first_name);
        TextView lastName = convertView.findViewById(R.id.tv_last_name);
        TextView contactNumber = convertView.findViewById(R.id.tv_contact_number);
        TextView email = convertView.findViewById(R.id.tv_email);
        TextView dateIn = convertView.findViewById(R.id.tv_date_in);
        TextView dateOut = convertView.findViewById(R.id.tv_date_out);

        firstName.setText(cursor.getString(cursor.getColumnIndexOrThrow("guest_firstname")));
        lastName.setText(cursor.getString(cursor.getColumnIndexOrThrow("guest_lastname")));
        contactNumber.setText(cursor.getString(cursor.getColumnIndexOrThrow("guest_contact_number")));
        email.setText(cursor.getString(cursor.getColumnIndexOrThrow("guest_email")));
        dateIn.setText(cursor.getString(cursor.getColumnIndexOrThrow("date_in")));
        dateOut.setText(cursor.getString(cursor.getColumnIndexOrThrow("date_out")));

        return convertView;
    }
}

package com.example.greenleafhotelapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class GuestReservationAdapter extends CursorAdapter {

    public GuestReservationAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.activity_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvFirstName = view.findViewById(R.id.tv_first_name);
        TextView tvLastName = view.findViewById(R.id.tv_last_name);
        TextView tvContactNumber = view.findViewById(R.id.tv_contact_number);
        TextView tvEmail = view.findViewById(R.id.tv_email);
        TextView tvDateIn = view.findViewById(R.id.tv_date_in);
        TextView tvDateOut = view.findViewById(R.id.tv_date_out);

        String firstName = cursor.getString(cursor.getColumnIndexOrThrow("guest_firstname"));
        String lastName = cursor.getString(cursor.getColumnIndexOrThrow("guest_lastname"));
        String contactNumber = cursor.getString(cursor.getColumnIndexOrThrow("guest_contact_number"));
        String email = cursor.getString(cursor.getColumnIndexOrThrow("guest_email"));
        String dateIn = cursor.getString(cursor.getColumnIndexOrThrow("date_in"));
        String dateOut = cursor.getString(cursor.getColumnIndexOrThrow("date_out"));

        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvContactNumber.setText(contactNumber);
        tvEmail.setText(email);
        tvDateIn.setText(dateIn);
        tvDateOut.setText(dateOut);
    }
}

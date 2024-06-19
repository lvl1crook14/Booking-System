package com.example.greenleafhotelapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BookingSystem.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statements to create tables
    private static final String CREATE_TABLE_ROOM =
            "CREATE TABLE Room (" +
                    "room_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "room_price INTEGER NOT NULL, " +
                    "room_variation_id INTEGER, " +
                    "FOREIGN KEY (room_variation_id) REFERENCES Room_variation(room_variation_id))";

    private static final String CREATE_TABLE_GUEST =
            "CREATE TABLE Guest (" +
                    "guest_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "guest_firstname VARCHAR(50) NOT NULL, " +
                    "guest_lastname VARCHAR(50) NOT NULL, " +
                    "guest_contact_number INTEGER NOT NULL, " +
                    "guest_email VARCHAR(50) NOT NULL)";

    private static final String CREATE_TABLE_ROOM_RESERVED =
            "CREATE TABLE Room_reserved (" +
                    "reserved_room_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "number_of_rooms INTEGER NOT NULL, " +
                    "room_id INTEGER NOT NULL, " +
                    "reservation_id INTEGER NOT NULL, " +
                    "status VARCHAR(50) NOT NULL, " +
                    "FOREIGN KEY (room_id) REFERENCES Room(room_id), " +
                    "FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id))";

    private static final String CREATE_TABLE_ROOM_VARIATION =
            "CREATE TABLE Room_variation (" +
                    "room_variation_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "description VARCHAR(50) NOT NULL, " +
                    "maximum_person INTEGER NOT NULL)";

    private static final String CREATE_TABLE_RESERVATION =
            "CREATE TABLE Reservation (" +
                    "reservation_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "guest_id INTEGER NOT NULL, " +
                    "room_id INTEGER NOT NULL, " +
                    "date_in DATE NOT NULL, " +
                    "date_out DATE NOT NULL, " +
                    "payment_status VARCHAR(50) NOT NULL, " +
                    "FOREIGN KEY (guest_id) REFERENCES Guest(guest_id), " +
                    "FOREIGN KEY (room_id) REFERENCES Room(room_id))";

    private static final String CREATE_TABLE_OCCUPIED_ROOM =
            "CREATE TABLE Occupied_room (" +
                    "occupied_room_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "reservation_id INTEGER NOT NULL, " +
                    "room_id INTEGER NOT NULL, " +
                    "FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id), " +
                    "FOREIGN KEY (room_id) REFERENCES Room(room_id))";

    private static final String CREATE_TABLE_HOSTED =
            "CREATE TABLE Hosted (" +
                    "host_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "guest_id INTEGER NOT NULL, " +
                    "occupied_room_id INTEGER NOT NULL, " +
                    "FOREIGN KEY (guest_id) REFERENCES Guest(guest_id), " +
                    "FOREIGN KEY (occupied_room_id) REFERENCES Occupied_room(occupied_room_id))";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ROOM);
        sqLiteDatabase.execSQL(CREATE_TABLE_GUEST);
        sqLiteDatabase.execSQL(CREATE_TABLE_ROOM_RESERVED);
        sqLiteDatabase.execSQL(CREATE_TABLE_ROOM_VARIATION);
        sqLiteDatabase.execSQL(CREATE_TABLE_RESERVATION);
        sqLiteDatabase.execSQL(CREATE_TABLE_OCCUPIED_ROOM);
        sqLiteDatabase.execSQL(CREATE_TABLE_HOSTED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Room");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Guest");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Room_reserved");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Room_variation");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Reservation");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Occupied_room");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Hosted");
        onCreate(sqLiteDatabase);
    }
}
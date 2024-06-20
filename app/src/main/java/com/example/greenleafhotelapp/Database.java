package com.example.greenleafhotelapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BookingSystem.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_ROOM = "Room";
    private static final String TABLE_GUEST = "Guest";
    private static final String TABLE_ROOM_RESERVED = "Room_reserved";
    private static final String TABLE_ROOM_VARIATION = "Room_variation";
    private static final String TABLE_RESERVATION = "Reservation";
    private static final String TABLE_OCCUPIED_ROOM = "Occupied_room";
    private static final String TABLE_HOSTED = "Hosted";

    // Room Table Columns
    private static final String COLUMN_ROOM_ID = "room_id";
    private static final String COLUMN_ROOM_PRICE = "room_price";

    // Guest Table Columns
    private static final String COLUMN_GUEST_ID = "guest_id";
    private static final String COLUMN_GUEST_FIRSTNAME = "guest_firstname";
    private static final String COLUMN_GUEST_LASTNAME = "guest_lastname";
    private static final String COLUMN_GUEST_CONTACT_NUMBER = "guest_contact_number";
    private static final String COLUMN_GUEST_EMAIL = "guest_email";

    // Room Reserved Table Columns
    private static final String COLUMN_RESERVED_ROOM_ID = "reserved_room_id";
    private static final String COLUMN_NUMBER_OF_ROOMS = "number_of_rooms";
    private static final String COLUMN_RESERVATION_ID = "reservation_id";
    private static final String COLUMN_STATUS = "status";

    // Room Variation Table Columns
    private static final String COLUMN_ROOM_VARIATION_ID = "room_variation_id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_MAXIMUM_PERSON = "maximum_person";

    // Reservation Table Columns
    private static final String COLUMN_DATE_IN = "date_in";
    private static final String COLUMN_DATE_OUT = "date_out";

    // Occupied Room Table Columns
    private static final String COLUMN_OCCUPIED_ROOM_ID = "occupied_room_id";

    // Hosted Table Columns
    private static final String COLUMN_HOST_ID = "host_id";
    private static final String COLUMN_OCCUPIED_ROOM_ID_FOREIGN = "occupied_room_id";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "Creating tables...");
        // Create Room Table
        String CREATE_ROOM_TABLE = "CREATE TABLE " + TABLE_ROOM + "("
                + COLUMN_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ROOM_PRICE + " INTEGER NOT NULL,"
                + COLUMN_ROOM_VARIATION_ID + " INTEGER,"
                + "FOREIGN KEY (" + COLUMN_ROOM_VARIATION_ID + ") REFERENCES " + TABLE_ROOM_VARIATION + "(" + COLUMN_ROOM_VARIATION_ID + ")"
                + ")";
        db.execSQL(CREATE_ROOM_TABLE);

        // Create Guest Table
        String CREATE_GUEST_TABLE = "CREATE TABLE " + TABLE_GUEST + "("
                + COLUMN_GUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_GUEST_FIRSTNAME + " VARCHAR(50) NOT NULL,"
                + COLUMN_GUEST_LASTNAME + " VARCHAR(50) NOT NULL,"
                + COLUMN_GUEST_CONTACT_NUMBER + " INTEGER NOT NULL,"
                + COLUMN_GUEST_EMAIL + " VARCHAR(50) NOT NULL"
                + ")";
        db.execSQL(CREATE_GUEST_TABLE);

        // Create Room Reserved Table
        String CREATE_ROOM_RESERVED_TABLE = "CREATE TABLE " + TABLE_ROOM_RESERVED + "("
                + COLUMN_RESERVED_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NUMBER_OF_ROOMS + " INTEGER NOT NULL,"
                + COLUMN_ROOM_ID + " INTEGER NOT NULL,"
                + COLUMN_RESERVATION_ID + " INTEGER NOT NULL,"
                + COLUMN_STATUS + " VARCHAR(50) NOT NULL,"
                + "FOREIGN KEY (" + COLUMN_ROOM_ID + ") REFERENCES " + TABLE_ROOM + "(" + COLUMN_ROOM_ID + "),"
                + "FOREIGN KEY (" + COLUMN_RESERVATION_ID + ") REFERENCES " + TABLE_RESERVATION + "(" + COLUMN_RESERVATION_ID + ")"
                + ")";
        db.execSQL(CREATE_ROOM_RESERVED_TABLE);

        // Create Room Variation Table
        String CREATE_ROOM_VARIATION_TABLE = "CREATE TABLE " + TABLE_ROOM_VARIATION + "("
                + COLUMN_ROOM_VARIATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DESCRIPTION + " VARCHAR(50) NOT NULL,"
                + COLUMN_MAXIMUM_PERSON + " INTEGER NOT NULL"
                + ")";
        db.execSQL(CREATE_ROOM_VARIATION_TABLE);

        // Create Reservation Table
        String CREATE_RESERVATION_TABLE = "CREATE TABLE " + TABLE_RESERVATION + "("
                + COLUMN_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_GUEST_ID + " INTEGER NOT NULL,"
                + COLUMN_ROOM_ID + " INTEGER NOT NULL,"
                + COLUMN_DATE_IN + " DATE NOT NULL,"
                + COLUMN_DATE_OUT + " DATE NOT NULL,"
                + "FOREIGN KEY (" + COLUMN_GUEST_ID + ") REFERENCES " + TABLE_GUEST + "(" + COLUMN_GUEST_ID + "),"
                + "FOREIGN KEY (" + COLUMN_ROOM_ID + ") REFERENCES " + TABLE_ROOM + "(" + COLUMN_ROOM_ID + ")"
                + ")";
        db.execSQL(CREATE_RESERVATION_TABLE);

        // Create Occupied Room Table
        String CREATE_OCCUPIED_ROOM_TABLE = "CREATE TABLE " + TABLE_OCCUPIED_ROOM + "("
                + COLUMN_OCCUPIED_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_RESERVATION_ID + " INTEGER NOT NULL,"
                + COLUMN_ROOM_ID + " INTEGER NOT NULL,"
                + "FOREIGN KEY (" + COLUMN_RESERVATION_ID + ") REFERENCES " + TABLE_RESERVATION + "(" + COLUMN_RESERVATION_ID + "),"
                + "FOREIGN KEY (" + COLUMN_ROOM_ID + ") REFERENCES " + TABLE_ROOM + "(" + COLUMN_ROOM_ID + ")"
                + ")";
        db.execSQL(CREATE_OCCUPIED_ROOM_TABLE);

        // Create Hosted Table
        String CREATE_HOSTED_TABLE = "CREATE TABLE " + TABLE_HOSTED + "("
                + COLUMN_HOST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_GUEST_ID + " INTEGER NOT NULL,"
                + COLUMN_OCCUPIED_ROOM_ID_FOREIGN + " INTEGER NOT NULL,"
                + "FOREIGN KEY (" + COLUMN_GUEST_ID + ") REFERENCES " + TABLE_GUEST + "(" + COLUMN_GUEST_ID + "),"
                + "FOREIGN KEY (" + COLUMN_OCCUPIED_ROOM_ID_FOREIGN + ") REFERENCES " + TABLE_OCCUPIED_ROOM + "(" + COLUMN_OCCUPIED_ROOM_ID + ")"
                + ")";
        db.execSQL(CREATE_HOSTED_TABLE);
        Log.d("Database", "Tables created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Database", "Upgrading database...");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM_RESERVED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM_VARIATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OCCUPIED_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOSTED);
        onCreate(db);
        Log.d("Database", "Database upgraded successfully");
    }

    // Method to retrieve guest and reservation data with '_id' alias
    public Cursor getGuestReservationData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT g." + COLUMN_GUEST_ID + " AS _id, g." + COLUMN_GUEST_FIRSTNAME + ", g." + COLUMN_GUEST_LASTNAME + ", "
                + "g." + COLUMN_GUEST_CONTACT_NUMBER + ", g." + COLUMN_GUEST_EMAIL + ", "
                + "r." + COLUMN_DATE_IN + ", r." + COLUMN_DATE_OUT
                + " FROM " + TABLE_GUEST + " g"
                + " INNER JOIN " + TABLE_RESERVATION + " r"
                + " ON g." + COLUMN_GUEST_ID + " = r." + COLUMN_GUEST_ID;
        Log.d("Database", "Executing query: " + query);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            Log.d("Database", "getGuestReservationData: cursor count = " + cursor.getCount());
        } else {
            Log.d("Database", "getGuestReservationData: cursor is null");
        }
        return cursor;
    }

    public Cursor getAllGuests() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GUEST, null);
        Log.d("Database", "getAllGuests: cursor count = " + cursor.getCount());
        return cursor;
    }

    public long addSimpleRoomReservation(String firstName, String lastName, int contactNumber, String email, int roomId, String dateIn, String dateOut) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues guestValues = new ContentValues();
        guestValues.put(COLUMN_GUEST_FIRSTNAME, firstName);
        guestValues.put(COLUMN_GUEST_LASTNAME, lastName);
        guestValues.put(COLUMN_GUEST_CONTACT_NUMBER, contactNumber);
        guestValues.put(COLUMN_GUEST_EMAIL, email);

        db.beginTransaction();
        try {
            long guestId = db.insert(TABLE_GUEST, null, guestValues);
            if (guestId == -1) {
                Log.e("Database", "Failed to insert guest");
                return -1; // Insertion failed
            }

            ContentValues reservationValues = new ContentValues();
            reservationValues.put(COLUMN_GUEST_ID, guestId);
            reservationValues.put(COLUMN_ROOM_ID, roomId);
            reservationValues.put(COLUMN_DATE_IN, dateIn);
            reservationValues.put(COLUMN_DATE_OUT, dateOut);

            long reservationId = db.insert(TABLE_RESERVATION, null, reservationValues);
            if (reservationId == -1) {
                Log.e("Database", "Failed to insert reservation");
                return -1; // Insertion failed
            }

            db.setTransactionSuccessful();
            Log.d("Database", "Reservation added successfully");
            return reservationId;
        } catch (Exception e) {
            Log.e("Database", "Error adding reservation", e);
            return -1;
        } finally {
            db.endTransaction();
        }
    }
}

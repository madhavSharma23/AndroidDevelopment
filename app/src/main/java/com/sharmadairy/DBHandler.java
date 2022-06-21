package com.sharmadairy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sharmadairy.model.Customer;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "dairydb";

    private static final int DB_VERSION = 2;

    private static final String TABLE_NAME = "customers";
// customer table
    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String MOBILE_COL = "mobile";

    private static final String ADDRESS_COL = "address";

    private static final String SHIFT_COL = "shift";

    private static final String STATUS_COL = "status";

    private static final String START_DATE_COL = "startdate";

    private static final String QUANTITY_COL = "quantity";

    //Hazri table
    private static final String TABLE_HAZRI = "hazri";
    private static final String  HAZRI_ID_COL= "id";
    private static final String HAZRI_DATE_COL = "date";
    private static final String HAZRI_CUST_ID_COL = "cust_id";
    private static final String HAZRI_QUANTITY_COL = "quantity";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//create customer table
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + ADDRESS_COL + " TEXT,"
                + SHIFT_COL + " TEXT,"
                + STATUS_COL + " TEXT,"
                + START_DATE_COL + " TEXT,"
                + QUANTITY_COL + " TEXT,"
                + MOBILE_COL + " TEXT)";
//Create Hazri table
        String query1 = "CREATE TABLE " + TABLE_HAZRI + " ("
                + HAZRI_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HAZRI_DATE_COL + " TEXT,"
                + HAZRI_CUST_ID_COL + " INTEGER,"
                + HAZRI_QUANTITY_COL + " TEXT)";
        db.execSQL(query);
        db.execSQL(query1);
    }

    public void addNewCustomer(String customerName, String mobileNumber,
                               String shift, String startDate,
                               String quantity, String address,
                               String status
    ) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL, customerName);
        values.put(MOBILE_COL, mobileNumber);
        values.put(SHIFT_COL, shift);
        values.put(START_DATE_COL, startDate);
        values.put(ADDRESS_COL, address);
        values.put(STATUS_COL, status);
        values.put(QUANTITY_COL, quantity);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }
    //adding new Hazri
    public void addNewHazri(String date,int custId,String quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HAZRI_DATE_COL, date);
        values.put(HAZRI_CUST_ID_COL, custId);
        values.put(HAZRI_QUANTITY_COL, quantity);
        db.insert(TABLE_HAZRI, null, values);
        db.close();
    }

    public ArrayList<Customer> readAllData() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor customerCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Customer> courseModalArrayList = new ArrayList<>();

        if (customerCursor.moveToFirst()) {
            do {
                courseModalArrayList.add(new Customer(customerCursor.getString(1),
                        customerCursor.getString(7),
                        customerCursor.getString(3),
                        customerCursor.getString(4),
                        customerCursor.getString(2),
                        customerCursor.getString(6),
                        customerCursor.getString(5))
                );
            } while (customerCursor.moveToNext());
        }
        customerCursor.close();
        return courseModalArrayList;
    }

    public ArrayList<Customer> readFilteredDataOnShift(String shift) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor customerCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+SHIFT_COL+" = \""+shift+"\"", null);
        ArrayList<Customer> courseModalArrayList = new ArrayList<>();

        if (customerCursor.moveToFirst()) {
            do {
                courseModalArrayList.add(new Customer(customerCursor.getString(1),
                        customerCursor.getString(7),
                        customerCursor.getString(3),
                        customerCursor.getString(4),
                        customerCursor.getString(2),
                        customerCursor.getString(6),
                        customerCursor.getString(5))
                );
            } while (customerCursor.moveToNext());
        }
        customerCursor.close();
        return courseModalArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HAZRI);
        onCreate(db);
    }
}
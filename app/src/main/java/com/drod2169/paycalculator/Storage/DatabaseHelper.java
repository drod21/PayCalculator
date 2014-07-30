package com.drod2169.paycalculator.Storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_JOBS_REFERENCE_TABLE = "create table jobs_reference (_id integer primary key autoincrement, job_table text not null, job_name text not null);";
    private static String DATABASE_TABLE = "shifts";
    private static final String CREATE_LOG_TABLE = "create table " + DATABASE_TABLE + " (_id integer primary key autoincrement, start_time integer not null, end_time integer not null, break integer not null, hours real not null, sales real, tips real, notes text, overtime real);";
    private static final String DATABASE_NAME = "worklog.db";
    private static final String DATABASE_TABLE_JOBS = "jobs_reference";
    private static final int DATABASE_VERSION = 4;
    private static final String JOB_TABLE_STRUCTURE = " (_id integer primary key autoincrement, start_time integer not null, end_time integer not null, break integer not null, hours real not null, sales real, tips real, notes text, overtime real);";
    public static final String KEY_BREAK = "break";
    public static final String KEY_END_TIME = "end_time";
    public static final String KEY_HOURS = "hours";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_START_TIME = "start_time";

    // Contacts table name
    private static final String TABLE_EMPLOYEES = "employees";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_EMPLOYEES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);

        // Create tables again
        onCreate(db);
    }
}

package com.drod2169.paycalculator.Storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by drod2169 on 7/17/14.
 */
public class DBAdapter {
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
    private final Context context;
    private SQLiteDatabase db;

    private static final String DATABASE_CREATE = "create table "
            + DATABASE_NAME + "(" + KEY_ROWID
            + " integer primary key autoincrement, " + DATABASE_TABLE
            + " text not null);";

    public DBAdapter(Context mContext) {
        this.context = mContext;
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_LOG_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBAdapter.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data"
        );
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_LOG_TABLE);
        onCreate(db);
    }


}

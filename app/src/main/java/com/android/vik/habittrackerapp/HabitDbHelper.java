package com.android.vik.habittrackerapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "habittracker.db";
    public static final int DB_VERSION = 2;

    private Context context;

    public HabitDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + HabitTrackerContract.HabitEntry.TABLE + " ( " +
                HabitTrackerContract.HabitEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HabitTrackerContract.HabitEntry.COL_HABIT_TITLE + " TEXT NOT NULL," +
                HabitTrackerContract.HabitEntry.COL_HABIT_DAYS + " " +
                "INTEGER NOT NULL );";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HabitTrackerContract.HabitEntry.TABLE);
        onCreate(db);
    }

    public void deleteDatabase() {
        context.deleteDatabase(DB_NAME);
    }

}

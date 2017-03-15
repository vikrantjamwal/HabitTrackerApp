package com.android.vik.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    HabitDbHelper mHelper;
    SQLiteDatabase db;
    ContentValues values = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHelper = new HabitDbHelper(this);
        db = mHelper.getWritableDatabase();

        insertData("coding", "54");
        insertData("swimming", "25");
        readFromDB(mHelper.getReadableDatabase());
        deleteAllData();
        updateData("writing", "10");
        updateData("playing", "40");
    }

    public void insertData(String habit, String days) {
        values.put(HabitTrackerContract.HabitEntry.COL_HABIT_TITLE, habit);
        values.put(HabitTrackerContract.HabitEntry.COL_HABIT_DAYS, days);
        db.insert(HabitTrackerContract.HabitEntry.TABLE, null, values);
    }

    private Cursor readFromDB(SQLiteDatabase db) {
        String[] projection = {
                HabitTrackerContract.HabitEntry.ID, HabitTrackerContract.HabitEntry.COL_HABIT_TITLE,
                HabitTrackerContract.HabitEntry.COL_HABIT_DAYS
        };
        String sortOrder = HabitTrackerContract.HabitEntry.ID + " ASC";
        Cursor cursor =
                db.query(HabitTrackerContract.HabitEntry.TABLE, projection, null, null, null, null,
                        sortOrder);
        return cursor;
    }

    public void deleteAllData() {
        db.delete(HabitTrackerContract.HabitEntry.TABLE, null, null);
    }

    public void updateData(String habit, String days) {
        values.put(HabitTrackerContract.HabitEntry.COL_HABIT_DAYS, days);
        db.update(HabitTrackerContract.HabitEntry.TABLE,
                values,
                HabitTrackerContract.HabitEntry.COL_HABIT_TITLE + "= ?",
                new String[]{habit});
    }
}

package com.android.vik.habittrackerapp;

import android.provider.BaseColumns;

public class HabitTrackerContract {

    public HabitTrackerContract() {

    }

    public class HabitEntry implements BaseColumns {
        public static final String TABLE = "habits";
        public static final String ID = "id";
        public static final String COL_HABIT_TITLE = "title";
        public static final String COL_HABIT_DAYS = "days";
    }
}
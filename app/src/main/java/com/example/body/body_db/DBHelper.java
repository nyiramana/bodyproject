package com.example.body.body_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static final String TABLE_NAME = "tbl_body";
    static final String _ID = "body_id";
    static final String BODYCOLOR = "body_color";
    static final String BODYHEIGHT = "body_height";
    static final String BODYWEIGHT = "body_weight";
    static final String DISABILITY = "disability";
    static final String ADDED_ON = "added_on";
    private static final String DB_NAME = "DB_BODY";
    private static final int DB_VERSION = 3;
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BODYCOLOR + " TEXT NOT NULL, " + BODYHEIGHT + " TEXT NOT NULL, " +BODYWEIGHT + " TEXT NOT NULL, "
            + DISABILITY + " INTEGER DEFAULT 0, " + ADDED_ON +
        " TEXT DEFAULT CURRENT_TIMESTAMP);";

    DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
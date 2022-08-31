package com.example.body.body_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBHandler {
    private DBHelper dbHelper;
    private final Context context;
    private SQLiteDatabase database;

    public DBHandler(Context c) {
        context = c;
    }

    public void open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insert(String bodycolor, String bodyheight,String bodyweight ,boolean disability) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.BODYCOLOR, bodycolor);
        contentValue.put(DBHelper.BODYHEIGHT, bodyheight);
        contentValue.put(DBHelper.BODYWEIGHT, bodyweight);
        contentValue.put(DBHelper.DISABILITY,disability);
        return database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor getSmellTypes() {
        String[] columns = new String[]{DBHelper._ID, DBHelper.BODYCOLOR, DBHelper.BODYHEIGHT,DBHelper.BODYWEIGHT, DBHelper.DISABILITY};
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, "body_id DESC");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
package com.example.app67.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shriji on 20/3/18.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_FILE_NAME = "jayesh.db";
    public static final int DB_VERSION = 4;

    public DBHelper(Context context){
        super(context, DB_FILE_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ItemTable.SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ItemTable.SQL_DELETE);
        onCreate(db);
    }
}

package com.example.app67.database;

/**
 * Created by shriji on 20/3/18.
 */

public class ItemTable {

    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ID = "itemId";
    public static final String COLUMN_NAME = "itemName";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_POSITION = "sortPosition";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_IS_ACTIVE = "statues";

    public static final String[] ALL_COLUMN = {COLUMN_ID,COLUMN_NAME,COLUMN_DESCRIPTION
            ,COLUMN_CATEGORY,COLUMN_POSITION,COLUMN_PRICE,COLUMN_IMAGE,COLUMN_IS_ACTIVE};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_CATEGORY + " TEXT," +
                    COLUMN_POSITION + " INTEGER," +
                    COLUMN_PRICE + " REAL," +
                    COLUMN_IS_ACTIVE + " INTEGER DEFAULT 1,"+
                    COLUMN_IMAGE + " TEXT" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE IF EXISTS " + TABLE_ITEMS;

}

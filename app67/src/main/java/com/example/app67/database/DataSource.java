package com.example.app67.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.app67.database.model.DataItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shriji on 20/3/18.
 */

public class DataSource {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDBHelper;

    public DataSource(Context context) {
        mContext = context;
        mDBHelper = new DBHelper(mContext);
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void Open(){
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void Close(){
        mDBHelper.close();
    }

    public DataItem createItem(DataItem item){
        ContentValues values = item.toValues();
        mDatabase.insert(ItemTable.TABLE_ITEMS,null,values);
        return item;
    }

    public long getDataItemCount(){
        return DatabaseUtils.queryNumEntries(mDatabase,ItemTable.TABLE_ITEMS);
    }

    public void seedDatabase(List<DataItem> mDataItemList){
        long numItems = getDataItemCount();
        if (numItems == 0) {
            for (DataItem item : mDataItemList) {
                createItem(item);
            }
            Toast.makeText(mContext, "Data inserted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Data already inserted !", Toast.LENGTH_SHORT).show();
        }
    }

    public List<DataItem> getAllItem(String category){
        List<DataItem> dataItems = new ArrayList<>();
        Cursor cursor =  null;
        if(category == null){
            String[] conditions = {"1"};
            cursor = mDatabase.query(ItemTable.TABLE_ITEMS,ItemTable.ALL_COLUMN,
                    ItemTable.COLUMN_IS_ACTIVE+" =?",conditions,null,null,
                    ItemTable.COLUMN_CATEGORY+" DESC");
        }else {
            String[] conditions = {category,"1"};
            cursor = mDatabase.query(ItemTable.TABLE_ITEMS,ItemTable.ALL_COLUMN,
                    ItemTable.COLUMN_CATEGORY+"=? AND "+ItemTable.COLUMN_IS_ACTIVE+" =?",
                    conditions,null,null,
                    ItemTable.COLUMN_CATEGORY+" DESC");
        }
      while (cursor.moveToNext()){
        DataItem items = new DataItem();
        items.setItemId(cursor.getInt(cursor.getColumnIndex(ItemTable.COLUMN_ID)));
        items.setItemName(cursor.getString(cursor.getColumnIndex(ItemTable.COLUMN_NAME)));
        items.setDescription(cursor.getString(cursor.getColumnIndex(ItemTable.COLUMN_DESCRIPTION)));
        items.setCategory(cursor.getString(cursor.getColumnIndex(ItemTable.COLUMN_CATEGORY)));
        items.setSortPosition(cursor.getInt(cursor.getColumnIndex(ItemTable.COLUMN_POSITION)));
        items.setPrice(cursor.getDouble(cursor.getColumnIndex(ItemTable.COLUMN_PRICE)));
        items.setImage(cursor.getString(cursor.getColumnIndex(ItemTable.COLUMN_IMAGE)));
        dataItems.add(items);
    }
    return dataItems;
    }

    public void deleteItemByID(int itemID){
     //   mDatabase.delete(ItemTable.TABLE_ITEMS,ItemTable.COLUMN_ID+" =?",new String[]{Integer.toString(itemID)});
        ContentValues cv = new ContentValues();
        cv.put(ItemTable.COLUMN_IS_ACTIVE,0);
        mDatabase.update(ItemTable.TABLE_ITEMS,cv,ItemTable.COLUMN_ID+" =?",new String[]{Integer.toString(itemID)});

    }

    public void updateItemByID(int itemID,String newText){

        ContentValues cv = new ContentValues();
        cv.put(ItemTable.COLUMN_NAME,newText);
        mDatabase.update(ItemTable.TABLE_ITEMS,cv,ItemTable.COLUMN_ID+" =?",new String[]{Integer.toString(itemID)});
    }

    public void undoItem(){
        //   mDatabase.delete(ItemTable.TABLE_ITEMS,ItemTable.COLUMN_ID+" =?",new String[]{Integer.toString(itemID)});
        ContentValues cv = new ContentValues();
        cv.put(ItemTable.COLUMN_IS_ACTIVE,1);
        mDatabase.update(ItemTable.TABLE_ITEMS,cv,null,null);
    }

    public void undoItemByID(int lastSelectedID){
        ContentValues cv = new ContentValues();
        cv.put(ItemTable.COLUMN_IS_ACTIVE,1);
        mDatabase.update(ItemTable.TABLE_ITEMS,cv,ItemTable.COLUMN_ID+" =?",new String[]{Integer.toString(lastSelectedID)});
    }

    public void deleteSelectedItemByID(List<Integer> itemIdList){
        //   mDatabase.delete(ItemTable.TABLE_ITEMS,ItemTable.COLUMN_ID+" =?",new String[]{Integer.toString(itemID)});
        ContentValues cv = new ContentValues();
        cv.put(ItemTable.COLUMN_IS_ACTIVE,0);
        for(Integer itemID:itemIdList){
            mDatabase.update(ItemTable.TABLE_ITEMS,cv,ItemTable.COLUMN_ID+" =?",new String[]{Integer.toString(itemID)});
        }

    }
}

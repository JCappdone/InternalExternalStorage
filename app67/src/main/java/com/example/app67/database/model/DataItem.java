package com.example.app67.database.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.app67.database.ItemTable;

public class DataItem implements Parcelable {

    private int itemId;
    private String itemName;
    private String category;
    private String description;
    private int sortPosition;
    private double price;
    private String image;

    public DataItem(int itemId, String itemName, String category, String description, int sortPosition, double price, String image) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.sortPosition = sortPosition;
        this.price = price;
        this.image = image;
    }

    public DataItem() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSortPosition() {
        return sortPosition;
    }

    public void setSortPosition(int sortPosition) {
        this.sortPosition = sortPosition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ContentValues toValues(){
        ContentValues values = new ContentValues(6);
   //     values.put(ItemTable.COLUMN_ID,itemId);
        values.put(ItemTable.COLUMN_NAME,itemName);
        values.put(ItemTable.COLUMN_CATEGORY,category);
        values.put(ItemTable.COLUMN_DESCRIPTION,description);
        values.put(ItemTable.COLUMN_POSITION,sortPosition);
        values.put(ItemTable.COLUMN_PRICE,price);
        values.put(ItemTable.COLUMN_IMAGE,image);
        return values;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.itemId);
        dest.writeString(this.itemName);
        dest.writeString(this.category);
        dest.writeString(this.description);
        dest.writeInt(this.sortPosition);
        dest.writeDouble(this.price);
        dest.writeString(this.image);
    }

    protected DataItem(Parcel in) {
        this.itemId = in.readInt();
        this.itemName = in.readString();
        this.category = in.readString();
        this.description = in.readString();
        this.sortPosition = in.readInt();
        this.price = in.readDouble();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel source) {
            return new DataItem(source);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
}
package com.example.app67.CallBacks;

import com.example.app67.database.model.DataItem;

/**
 * Created by shriji on 20/3/18.
 */

public interface ActionListionr {

    void onLongPress(int position,DataItem item);
    void onSingleClick(int position,DataItem item);
    void onCheckChange(int position,DataItem item,boolean isChecked);
}

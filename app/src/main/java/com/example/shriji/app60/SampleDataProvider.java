package com.example.shriji.app60;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shriji on 19/3/18.
 */

public class SampleDataProvider {

    public static List<DataModel> dataItems;
    public static Map<String, DataModel> dataMap;

    static {
        dataItems = new ArrayList<>();
        dataMap = new HashMap<>();

        addItem(new DataModel("1","abc","img1.jpeg"));
        addItem(new DataModel("2","xyz","img1.jpeg"));
    }

    private static void addItem(DataModel items){
        dataItems.add(items);
        dataMap.put(items.getmId(),items);
    }


}

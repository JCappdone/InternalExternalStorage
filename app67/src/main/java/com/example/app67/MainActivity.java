package com.example.app67;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.app67.CallBacks.ActionListionr;
import com.example.app67.adpters.ItemViewAdpter;
import com.example.app67.database.DataSource;
import com.example.app67.database.model.DataItem;
import com.example.app67.sample.SampleDataProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    DataSource mDataSource;
    List<DataItem> mDataItemList = SampleDataProvider.dataItemList;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    String[] mCategories;
    List<DataItem> listFromDB;
    private ItemViewAdpter mAdpter;
    public int lastUndoID;

    List<Integer> selectedItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDataSource = new DataSource(this);
        mDataSource.Open();
        Toast.makeText(this, "Database Acquire", Toast.LENGTH_SHORT).show();
        mDataSource.seedDatabase(mDataItemList);

        selectedItem = new ArrayList<>();

        mCategories = getResources().getStringArray(R.array.categories);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayDataItem(null);

    }

    private void displayDataItem(String category) {
        listFromDB = mDataSource.getAllItem(category);
        mAdpter = new ItemViewAdpter(this, listFromDB, new ActionListionr() {
            @Override
            public void onLongPress(int pos, DataItem item) {
                DataSource data = new DataSource(MainActivity.this);
                lastUndoID = item.getItemId();
                data.deleteItemByID(item.getItemId());
                listFromDB.clear();
                listFromDB.addAll(mDataSource.getAllItem(null));
                mAdpter.notifyDataSetChanged();
            }

            @Override
            public void onSingleClick(int position, DataItem item) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("item", item);
                startActivityForResult(intent, 1001);

            }

            @Override
            public void onCheckChange(int position, DataItem item, boolean isChecked) {
                if (isChecked) {
                    selectedItem.add(item.getItemId());
                } else {
                    selectedItem.remove(item.getItemId());
                }
            }
        });
        mRecyclerView.setAdapter(mAdpter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1001 && resultCode == RESULT_OK){
            listFromDB.clear();
            mDataSource = new DataSource(this);
            listFromDB.addAll(mDataSource.getAllItem(null));
            mAdpter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.Close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.Open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salad_menu:
                //TODO implement
                displayDataItem("Salads");
                return true;
            case R.id.starters_menu:
                //TODO implement
                displayDataItem("Starters");
                return true;
            case R.id.entrees_menu:
                //TODO implement
                displayDataItem("Entrees");
                return true;
            case R.id.desserts_menu:
                //TODO implement
                displayDataItem("Desserts");
                return true;
            case R.id.drinks_menu:
                //TODO implement
                displayDataItem("Drinks");
                return true;
            case R.id.all_item_menu:
                //TODO implement
                displayDataItem(null);
                return true;
            case R.id.undo_menu:
                //TODO implement
                DataSource data = new DataSource(MainActivity.this);
                data.undoItem();
                listFromDB.clear();
                listFromDB.addAll(mDataSource.getAllItem(null));
                mAdpter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdpter);
                return true;
            case R.id.last_undo_menu:
                //TODO implement
                mDataSource.undoItemByID(lastUndoID);
                listFromDB.clear();
                listFromDB.addAll(mDataSource.getAllItem(null));
                mAdpter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdpter);
                return true;
            case R.id.delete_menu:
                //TODO implement
                mDataSource.deleteSelectedItemByID(selectedItem);
                listFromDB.clear();
                listFromDB.addAll(mDataSource.getAllItem(null));
                mAdpter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdpter);


                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

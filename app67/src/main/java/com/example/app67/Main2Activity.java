package com.example.app67;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.app67.database.DataSource;
import com.example.app67.database.model.DataItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText mEditText;
    @BindView(R.id.button)
    Button mButton;

    DataSource mDataSource;
    private DataItem mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        mItem = getIntent().getExtras().getParcelable("item");
        String oldText = mItem.getItemName();
        mEditText.setText(oldText);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        String text = mEditText.getText().toString();
        mDataSource = new DataSource(this);
        mDataSource.updateItemByID(mItem.getItemId(),text);

        Intent resultIntent = new Intent();
        setResult(RESULT_OK,resultIntent);
        finish();

    }
}

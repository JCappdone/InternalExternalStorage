package com.example.shriji.app60;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.img2)
    ImageView img2;
    private InputStream inputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);


        DataModel data = getIntent().getExtras().getParcelable(DataAdpter.ITEM_KEY);
        String  name = data.getmName();
        String image = data.getmImage();

        if (data != null) {
            Toast.makeText(this, "Item  Recivied", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Item not Recivied", Toast.LENGTH_SHORT).show();
        }

       txt2.setText(name);

        try {
            inputStream = getAssets().open(image);
            Drawable d = Drawable.createFromStream(inputStream,null);
            img2.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

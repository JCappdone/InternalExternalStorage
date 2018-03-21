package com.example.app62;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    private static final String FILE_NAME = "jayesh.txt";
    @BindView(R.id.btnCreate2)
    Button btnCreate2;
    @BindView(R.id.btnRead2)
    Button btnRead2;
    @BindView(R.id.btnDelete2)
    Button btnDelete2;
    @BindView(R.id.imgView2)
    ImageView imgView2;
    @BindView(R.id.textView2)
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btnCreate2, R.id.btnRead2, R.id.btnDelete2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCreate2:
                String string = getString(R.string.jayesh_text);

                FileOutputStream fileOutputStream = null;
                File file = new File(FILE_NAME);

                try {
                    fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fileOutputStream.write(string.getBytes());
                    Toast.makeText(this, "File Written: " + FILE_NAME, Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Exception " + e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.btnRead2:

                File file2 = new File(getFilesDir(), FILE_NAME);
                if (file2.exists()) {
                    textView2.setText(read_file(this,FILE_NAME));
                    Toast.makeText(this, "File exist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "File Not Exist", Toast.LENGTH_SHORT).show();
                }
                Log.d("text", "onReadClicked: "+read_file(this,FILE_NAME));
                break;
            case R.id.btnDelete2:
                File file1 = new File(getFilesDir(), FILE_NAME);
                if (file1.exists()) {
                    deleteFile(FILE_NAME);
                    Toast.makeText(this, "File Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "File Not Exist", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public String read_file(Context context, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }

}


package com.example.app62;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "jayesh_Extrenal.txt";
    private static final int MY_PERMISSIONS_REQUEST = 1001;
    @BindView(R.id.btnCreate)
    Button btnCreate;
    @BindView(R.id.btnRead)
    Button btnRead;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.imgView)
    ImageView imgView;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            checkPermissionRunTime();
        }
    }

    private void checkPermissionRunTime() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private File getFile() {
        return new File(Environment.getExternalStorageDirectory(), FILE_NAME);
    }


    @OnClick({R.id.btnCreate, R.id.btnRead, R.id.btnDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCreate:

                String string = getString(R.string.jayesh_text);

                FileOutputStream fileOutputStream = null;
                File file = getFile();

                try {
                    fileOutputStream = new FileOutputStream(file);
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
            case R.id.btnRead:
                File file2 = getFile();
                if (file2.exists()) {
                    textView.setText(read_file());
                    Toast.makeText(this, "File exist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "File Not Exist", Toast.LENGTH_SHORT).show();
                }
                Log.d("text", "onReadClicked: " + read_file());


                break;
            case R.id.btnDelete:
                File file1 = getFile();
                if (file1.exists()) {
                    file1.delete();
                    Toast.makeText(this, "File Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "File Not Exist", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public String read_file() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getFile()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            bufferedReader.close();
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

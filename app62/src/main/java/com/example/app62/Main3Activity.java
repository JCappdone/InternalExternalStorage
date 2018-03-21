package com.example.app62;

import android.Manifest;
import android.app.admin.DevicePolicyManager;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 1002;
    @BindView(R.id.btnCreate3)
    Button btnCreate3;
    @BindView(R.id.btnRead3)
    Button btnRead3;
    @BindView(R.id.btnDelete3)
    Button btnDelete3;
    @BindView(R.id.imgView3)
    ImageView imgView3;
    @BindView(R.id.textView3)
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Toast.makeText(this, "You have Permission", Toast.LENGTH_SHORT).show();
        } else {
            checkRuntimePermission();
        }
    }


    private void checkRuntimePermission() {
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

    @OnClick({R.id.btnCreate3, R.id.btnRead3, R.id.btnDelete3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCreate3:
                copyAssets();


                break;
            case R.id.btnRead3:
                File imageFile = new  File("/sdcard//Aqeel/Images/img1.jpeg");
                if(imageFile.exists()){
                    imgView3.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
                }else{
                    Toast.makeText(this, "File not exist", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.btnDelete3:
                deleteFromExternalStorage("Images");
                imgView3.setImageResource(R.drawable.ic_launcher_background);
                break;
        }
    }

    private void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("img");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }

        for (String filename : files) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetManager.open("img/" + filename);
                    //  File outFile = new File(Environment.getExternalStorageDirectory().toString()+"/Aqeel/Images");
                    File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Aqeel/Images");
                    if (!folder.exists()) {
                        folder.mkdirs();
                        File outFile = new File(folder, filename);
                        out = new FileOutputStream(outFile);
                        copyFile(in, out);
                        in.close();
                        in = null;
                        out.flush();
                        out.close();
                        out = null;
                        Toast.makeText(this, "task complete", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(this, "file  Exits", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + filename, e);
                }
            }

        }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public void deleteFromExternalStorage(String fileName) {
        String fullPath = "sdcard/Aqeel";
        try {
            File file = new File(fullPath, fileName);
            if (file.exists()){
                String deleteCmd = "rm -r " + fullPath;
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec(deleteCmd);
                } catch (IOException e) {

                }
               // file.delete();
                Toast.makeText(this, "file Delete", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "file not exist", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("App", "Exception while deleting file " + e.getMessage());
        }
    }
}

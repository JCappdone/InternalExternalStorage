package com.example.app62;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

public class Main4Activity extends AppCompatActivity {

    @BindView(R.id.btnCreate4)
    Button btnCreate4;
    @BindView(R.id.btnRead4)
    Button btnRead4;
    @BindView(R.id.btnDelete4)
    Button btnDelete4;
    @BindView(R.id.imgView4)
    ImageView imgView4;
    @BindView(R.id.textView4)
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btnCreate4, R.id.btnRead4, R.id.btnDelete4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCreate4:
                copyAssets();
                break;
            case R.id.btnRead4:
                break;
            case R.id.btnDelete4:
                trimCache(this);
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
           /*     File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Aqeel/Images");
                if (!folder.exists()) {
                    folder.mkdirs();*/
                   // File outFile = File.createTempFile(filename, null,getCacheDir());
                File outFile = new File(getCacheDir(),filename);
                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;
                    Toast.makeText(this, "task complete", Toast.LENGTH_SHORT).show();

             /*   }else{
                    Toast.makeText(this, "file  Exits", Toast.LENGTH_SHORT).show();
                }*/

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

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // The directory is now empty so delete it
        return dir.delete();
    }
}

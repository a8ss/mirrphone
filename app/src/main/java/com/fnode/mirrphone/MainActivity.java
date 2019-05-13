package com.fnode.mirrphone;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "sms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();

        this.checkAndRequestPermission();
    }

    protected void initView() {
        Button btnReadFirst = findViewById(R.id.readFirst);

        btnReadFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click....");
                ContentResolver contentResolver = getContentResolver();

                Cursor cursor = contentResolver.query(Uri.parse("content://sms/inbox"),
                        new String[]{"_id", "address", "read", "body"}, "", null, "date desc");

                if (cursor == null) {
                    Log.e(TAG, "cursor is null");
                    return;
                }

                while (cursor.moveToNext()) {
                    Log.d(TAG, cursor.getString(cursor.getColumnIndex("_id"))
                            + " "
                            + cursor.getString(cursor.getColumnIndex("body"))
                    );
                }

                cursor.close();
            }
        });

    }

    protected void checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 100);
        }
    }

}


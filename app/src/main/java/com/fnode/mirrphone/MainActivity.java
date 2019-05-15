package com.fnode.mirrphone;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.CheckBox;
import android.widget.EditText;

import com.fnode.mirrphone.Send.Email;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainActivity";

    private static final String CNAME = "MirrPhoneConfig";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SenderConfig.mainActivity = MainActivity.this;
        SenderConfig.load(getShardP());
        this.initView();
        this.initSender();

        this.checkAndRequestPermission();

    }

    protected void initView() {

        this.initConfig();

        Button btnReadFirst = findViewById(R.id.readFirst);

        btnReadFirst.setOnClickListener(readLastSms());

        Button btnSendTestEmail = findViewById(R.id.sendTestEmail);
        btnSendTestEmail.setOnClickListener(sendTestEmail());
    }

    private void initConfig() {

        SenderConfig.load(getShardP());

        Button btnSave = findViewById(R.id.save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SenderConfig.save(getShardP().edit());
            }
        });

    }

    private void initSender() {
        if (SenderConfig.isSendEmail()) {
            initEmail();
        }
    }

    private void initEmail() {
        Email.setHostname(SenderConfig.getSmtpAddress());
        Email.setPort(SenderConfig.getPort());
        Email.setUser(SenderConfig.getUsername());
        Email.setSendFrom(SenderConfig.getFromAddress());
        Email.setPasswd(SenderConfig.getPassowrd());
        Email.setSendTo(SenderConfig.getToAddress());
    }


    private SharedPreferences getShardP() {
        return getSharedPreferences(CNAME, Context.MODE_PRIVATE);
    }

    private View.OnClickListener readLastSms() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click....");
                ContentResolver contentResolver = getContentResolver();

                Cursor cursor = contentResolver.query(Uri.parse("content://sms/inbox"),
                        new String[]{"_id", "address", "read", "body"}, null, null, "date desc");

                if (cursor == null) {
                    Log.e(TAG, "cursor is null");
                    return;
                }

                cursor.moveToNext();
                Log.d(TAG, cursor.getString(cursor.getColumnIndex("_id"))
                        + " "
                        + cursor.getString(cursor.getColumnIndex("body"))
                );
                cursor.close();
            }
        };
    }


    protected View.OnClickListener sendTestEmail() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email.send("MirrPhone test", "test email");
            }
        };
    }

    protected void checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 100);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
        }
    }

}


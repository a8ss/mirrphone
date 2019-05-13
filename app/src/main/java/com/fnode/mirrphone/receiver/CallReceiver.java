package com.fnode.mirrphone.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallReceiver extends BroadcastReceiver {

    public static final String TAG = "CallReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
        if (tm.getCallState() != TelephonyManager.CALL_STATE_IDLE) { //挂断后通知。等待接听时可能无网络
            return;
        }

        String number = intent.getStringExtra("incoming_number");
        if (!number.isEmpty()) {
            String body = "Incoming call: " + number;
            Log.d(TAG, body);
        }
        Log.d(TAG, number);

    }
}

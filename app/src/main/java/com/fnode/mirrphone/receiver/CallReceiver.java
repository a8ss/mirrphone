package com.fnode.mirrphone.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.fnode.mirrphone.Send.Email;

import java.util.Objects;

public class CallReceiver extends BroadcastReceiver {

    public static final String TAG = "CallReceiver";

    private static String mIncomingNumber = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Objects.requireNonNull(intent.getAction()).equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            return;
        }

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);

        if (tm.getCallState() == TelephonyManager.CALL_STATE_RINGING) { //来电才有来电号码
            mIncomingNumber = intent.getStringExtra("incoming_number");
            Log.d(TAG, "来电号码：" + mIncomingNumber);
        }

        if (tm.getCallState() == TelephonyManager.CALL_STATE_IDLE) { //挂断后通知。等待接听时可能无网络
            if (!mIncomingNumber.isEmpty()) {
                String body = "Incoming call: " + mIncomingNumber;
                Log.d(TAG, body);
                Email.send("您有了新来电", body);
                mIncomingNumber = null;
            }
        }

    }
}

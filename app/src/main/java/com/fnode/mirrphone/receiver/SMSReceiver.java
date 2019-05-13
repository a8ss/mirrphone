package com.fnode.mirrphone.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    protected static final String TAG = "SMSReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "收到了");

        Bundle bundle = intent.getExtras();
        assert bundle != null : "bundle is null";
        Object[] pdus = (Object[]) bundle.get("pdus");

        assert pdus != null : "pdus is null";
        SmsMessage[] smsMessage = new SmsMessage[pdus.length];
        for (int n = 0; n < pdus.length; n++) {
            smsMessage[n] = SmsMessage.createFromPdu((byte[]) pdus[n]);
        }

        StringBuilder sms = new StringBuilder();
        for (SmsMessage s : smsMessage) {
            sms.append(s.getMessageBody());
        }

        Log.d(TAG, sms.toString());
    }
}

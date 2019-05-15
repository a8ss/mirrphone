package com.fnode.mirrphone.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.fnode.mirrphone.Send.Email;

public class SMSReceiver extends BroadcastReceiver {

    protected static final String TAG = "SMSReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "收到了短信：");

        Bundle bundle = intent.getExtras();
        assert bundle != null : "bundle is null";
        Object[] pdus = (Object[]) bundle.get("pdus");

        assert pdus != null : "pdus is null";
        SmsMessage[] smsMessage = new SmsMessage[pdus.length];
        for (int n = 0; n < pdus.length; n++) {
            smsMessage[n] = SmsMessage.createFromPdu((byte[]) pdus[n]);
        }

        StringBuilder sms = new StringBuilder();
        String sender = "";
        for (SmsMessage s : smsMessage) {
            sms.append(s.getMessageBody());
            sender = s.getOriginatingAddress();
        }
        String subject = "您有了新短信";
        String body = sms.toString() + "【" + sender + "】";
        Log.d(TAG, sms.toString());
        Email.send(subject, body);
    }
}

package com.fnode.mirrphone;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SenderConfig {

    private static final String TAG = "SenderConfig";

    private static final String CNAME = "MirrPhoneConfig";

    private static String smtpAddress = "";
    private static String fromAddress = "";
    private static int port = 465;
    private static String username = "";
    private static String passowrd = "";
    private static String toAddress = "";
    private static String httpAddress = "";

    private static boolean sendEmail = true;
    private static boolean sendHttp = false;


    private static MainActivity mainActivity;

    private static HashMap<String, EditText> editTextHashMap = null;

    private static HashMap<String, CheckBox> checkBoxHashMap = null;

    private static SharedPreferences sharedPreferences = null;


    private static void load() {

        SenderConfig.setSmtpAddress(sharedPreferences.getString("smtpAddress", ""));
        SenderConfig.setPort(Integer.valueOf(sharedPreferences.getString("port", "465") + ""));
        SenderConfig.setUsername(sharedPreferences.getString("username", ""));
        SenderConfig.setPassowrd(sharedPreferences.getString("password", ""));
        SenderConfig.setFromAddress(sharedPreferences.getString("fromAddress", ""));
        SenderConfig.setToAddress(sharedPreferences.getString("toAddress", ""));
        SenderConfig.setHttpAddress(sharedPreferences.getString("httpAddress", ""));

        SenderConfig.setSendEmail(sharedPreferences.getBoolean("sendEmail", true));
        SenderConfig.setSendHttp(sharedPreferences.getBoolean("sendHttp", false));

    }

    static void init(MainActivity activity, SharedPreferences sharedP) {
        mainActivity = activity;
        sharedPreferences = sharedP;


        editTextHashMap = new HashMap<>();
        editTextHashMap.put("smtpAddress", (EditText) mainActivity.findViewById(R.id.smtpAddress));
        editTextHashMap.put("port", (EditText) mainActivity.findViewById(R.id.port));
        editTextHashMap.put("username", (EditText) mainActivity.findViewById(R.id.username));
        editTextHashMap.put("password", (EditText) mainActivity.findViewById(R.id.password));
        editTextHashMap.put("fromAddress", (EditText) mainActivity.findViewById(R.id.fromAddress));
        editTextHashMap.put("toAddress", (EditText) mainActivity.findViewById(R.id.toAddress));
        editTextHashMap.put("httpAddress", (EditText) mainActivity.findViewById(R.id.httpAddress));

        checkBoxHashMap = new HashMap<>();
        checkBoxHashMap.put("sendEmail", (CheckBox) mainActivity.findViewById(R.id.sendTypeEmail));
        checkBoxHashMap.put("sendHttp", (CheckBox) mainActivity.findViewById(R.id.sendTypeHttp));

        load();
        regOnChanged();
    }


    private static void regOnChanged() {

        for (final HashMap.Entry<String, EditText> editText : editTextHashMap.entrySet()) {
            editText.getValue().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, editText.getKey() + " " + s.toString());
                    sharedPreferences.edit().putString(editText.getKey(), s.toString()).apply();
                }
            });
        }

        for (final HashMap.Entry<String, CheckBox> checkBoxEntry : checkBoxHashMap.entrySet()) {
            checkBoxEntry.getValue().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    sharedPreferences.edit().putBoolean(checkBoxEntry.getKey(), isChecked).apply();
                }
            });
        }

    }

    public static String getSmtpAddress() {
        return smtpAddress;
    }

    public static void setSmtpAddress(String smtpAddress) {
        SenderConfig.smtpAddress = smtpAddress;
        editTextHashMap.get("smtpAddress").setText(smtpAddress);
    }

    public static String getFromAddress() {
        return fromAddress;
    }

    public static void setFromAddress(String fromAddress) {
        SenderConfig.fromAddress = fromAddress;
        editTextHashMap.get("fromAddress").setText(fromAddress);
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        SenderConfig.port = port;
        editTextHashMap.get("port").setText(String.valueOf(port));
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SenderConfig.username = username;
        editTextHashMap.get("username").setText(username);
    }

    public static String getPassowrd() {
        return passowrd;
    }

    public static void setPassowrd(String passowrd) {
        SenderConfig.passowrd = passowrd;
        editTextHashMap.get("password").setText(passowrd);
    }

    public static String getToAddress() {
        return toAddress;
    }

    public static void setToAddress(String toAddress) {
        SenderConfig.toAddress = toAddress;
        editTextHashMap.get("toAddress").setText(toAddress);
    }

    public static String getHttpAddress() {
        return httpAddress;
    }

    public static void setHttpAddress(String httpAddress) {
        SenderConfig.httpAddress = httpAddress;
        editTextHashMap.get("httpAddress").setText(httpAddress);
    }

    public static boolean isSendEmail() {
        return sendEmail;
    }

    public static void setSendEmail(boolean sendEmail) {
        SenderConfig.sendEmail = sendEmail;
        checkBoxHashMap.get("sendEmail").setChecked(sendEmail);
    }

    public static boolean isSendHttp() {
        return sendHttp;
    }

    public static void setSendHttp(boolean sendHttp) {
        SenderConfig.sendHttp = sendHttp;
        checkBoxHashMap.get("sendHttp").setChecked(sendHttp);
    }
}

package com.fnode.mirrphone;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.TextView;

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


    public static MainActivity mainActivity;


    public static void load(SharedPreferences sharedPreferences) {

        SenderConfig.setSmtpAddress(sharedPreferences.getString("smtpAddress", ""));
        updateSmtpAddress();
        SenderConfig.setPort(sharedPreferences.getInt("port", 465));
        updatePort();
        SenderConfig.setUsername(sharedPreferences.getString("username", ""));
        updateUsername();
        SenderConfig.setPassowrd(sharedPreferences.getString("password", ""));
        updatePassword();
        SenderConfig.setFromAddress(sharedPreferences.getString("fromAddress", ""));
        updateFromAddress();
        SenderConfig.setToAddress(sharedPreferences.getString("toAddress", ""));
        updateToAddress();
        SenderConfig.setHttpAddress(sharedPreferences.getString("httpAddress", ""));
        updateHttpAddress();
        SenderConfig.setSendEmail(sharedPreferences.getBoolean("sendEmail", true));
        SenderConfig.setSendHttp(sharedPreferences.getBoolean("sendHttp", false));
    }

    public static void save(SharedPreferences.Editor editor) {
        updateSmtpAddress();
        editor.putString("smtpAddress", SenderConfig.getSmtpAddress());
        updatePort();
        editor.putInt("port", SenderConfig.getPort());
        updateUsername();
        editor.putString("username", SenderConfig.getUsername());
        updatePassword();
        editor.putString("password", SenderConfig.getPassowrd());
        updateFromAddress();
        editor.putString("fromAddress", SenderConfig.getFromAddress());
        updateToAddress();
        editor.putString("toAddress", SenderConfig.getToAddress());
        updateHttpAddress();
        editor.putString("httpAddress", SenderConfig.getHttpAddress());
        updateSendEmail();
        editor.putBoolean("sendEmail", SenderConfig.isSendEmail());
        updateSendHttp();
        editor.putBoolean("sendHttp", SenderConfig.isSendHttp());

        editor.commit();
    }

    public static void updateSmtpAddress() {
        EditText smtpAddress = mainActivity.findViewById(R.id.smtpAddress);
        String txt = smtpAddress.getText().toString();

        if (!txt.equals("")) {
            setSmtpAddress(txt);
        } else {
            smtpAddress.setText(getSmtpAddress());
        }
    }

    public static void updatePort() {
        TextView port = mainActivity.findViewById(R.id.port);
        String txt = port.getText().toString();

        if (!txt.equals("")) {
            setPort(Integer.valueOf(txt));
        } else {
            port.setText(String.valueOf(getPort()));
        }
    }

    public static void updateUsername() {
        EditText username = mainActivity.findViewById(R.id.username);
        String txt = username.getText().toString();

        if (!txt.equals("")) {
            setUsername(txt);
        } else {
            username.setText(getUsername());
        }
    }

    public static void updatePassword() {
        EditText passowrd = mainActivity.findViewById(R.id.password);
        String txt = passowrd.getText().toString();
        if (!txt.equals("")) {
            setPassowrd(txt);
        } else {
            passowrd.setText(getPassowrd());
        }
    }

    public static void updateFromAddress() {
        EditText fromAddress = mainActivity.findViewById(R.id.fromAddress);
        String txt = fromAddress.getText().toString();
        if (!txt.equals("")) {
            setFromAddress(txt);
        } else {
            fromAddress.setText(getFromAddress());
        }
    }

    public static void updateToAddress() {
        EditText toAddress = mainActivity.findViewById(R.id.toAddress);
        String txt = toAddress.getText().toString();
        if (!txt.equals("")) {
            setToAddress(txt);
        } else {
            toAddress.setText(getToAddress());
        }
    }

    public static void updateHttpAddress() {
        EditText httpAddress = mainActivity.findViewById(R.id.httpAddress);
        String txt = httpAddress.getText().toString();
        if (!txt.equals("")) {
            setHttpAddress(txt);
        } else {
            httpAddress.setText(getHttpAddress());
        }
    }

    public static void updateSendEmail() {
        CheckBox sendEmail = mainActivity.findViewById(R.id.sendTypeEmail);
        setSendEmail(sendEmail.isChecked());
    }

    public static void updateSendHttp() {
        CheckBox sendHttp = mainActivity.findViewById(R.id.sendTypeHttp);
        setSendHttp(sendHttp.isChecked());
    }


    public static String getSmtpAddress() {
        return smtpAddress;
    }

    public static void setSmtpAddress(String smtpAddress) {
        SenderConfig.smtpAddress = smtpAddress;
    }

    public static String getFromAddress() {
        return fromAddress;
    }

    public static void setFromAddress(String fromAddress) {
        SenderConfig.fromAddress = fromAddress;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        SenderConfig.port = port;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SenderConfig.username = username;
    }

    public static String getPassowrd() {
        return passowrd;
    }

    public static void setPassowrd(String passowrd) {
        SenderConfig.passowrd = passowrd;
    }

    public static String getToAddress() {
        return toAddress;
    }

    public static void setToAddress(String toAddress) {
        SenderConfig.toAddress = toAddress;
    }

    public static String getHttpAddress() {
        return httpAddress;
    }

    public static void setHttpAddress(String httpAddress) {
        SenderConfig.httpAddress = httpAddress;
    }

    public static boolean isSendEmail() {
        return sendEmail;
    }

    public static void setSendEmail(boolean sendEmail) {
        SenderConfig.sendEmail = sendEmail;
    }

    public static boolean isSendHttp() {
        return sendHttp;
    }

    public static void setSendHttp(boolean sendHttp) {
        SenderConfig.sendHttp = sendHttp;
    }
}

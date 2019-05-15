package com.fnode.mirrphone.Send;


import android.util.Log;

import com.fnode.mirrphone.CachedThreadPool;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Email {

    private static final String TAG = "Email";

    private static int port = 465;
    private static String hostname = "";
    private static String sendTo = "";
    private static String sendFrom = "";
    private static String user = "";
    private static String passwd = "";
    private static String charset = "utf8";


    public static void send(final String subject, final String body) {
        CachedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    HtmlEmail email = new HtmlEmail();
                    email.setHostName(hostname);
                    email.setSmtpPort(port);
                    email.setCharset(charset);
                    email.addTo(sendTo);
                    email.setFrom(sendFrom);

                    if (port == 587) {
                        email.setTLS(true);
                    } else {
                        email.setSSL(true);
                    }

                    email.setAuthentication(user, passwd);
                    email.setSubject(subject);
                    email.setMsg(body);

                    email.send();
                } catch (EmailException e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    public static String getTAG() {
        return TAG;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Email.port = port;
    }

    public static String getHostname() {
        return hostname;
    }

    public static void setHostname(String hostname) {
        Email.hostname = hostname;
    }

    public static String getSendTo() {
        return sendTo;
    }

    public static void setSendTo(String sendTo) {
        Email.sendTo = sendTo;
    }

    public static String getSendFrom() {
        return sendFrom;
    }

    public static void setSendFrom(String sendFrom) {
        Email.sendFrom = sendFrom;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Email.user = user;
    }

    public static String getPasswd() {
        return passwd;
    }

    public static void setPasswd(String passwd) {
        Email.passwd = passwd;
    }

    public static String getCharset() {
        return charset;
    }

    public static void setCharset(String charset) {
        Email.charset = charset;
    }



}

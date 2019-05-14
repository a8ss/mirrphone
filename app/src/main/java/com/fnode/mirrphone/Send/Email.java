package com.fnode.mirrphone.Send;


import android.util.Log;

import com.fnode.mirrphone.CachedThreadPool;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Email {

    private static final String TAG = "Email";

    private int port = 465;
    private String hostname = "";
    private String sendTo = "";
    private String sendFrom = "";
    private String user = "";
    private String passwd = "";
    private String subject = "";
    private String body = "";

    private String charset = "utf8";

    public void send() {
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}

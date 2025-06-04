package org.EAL.setup_session;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import java.util.Properties;

public class SessionContext {

    public static Session startSession(){
        Properties sessionProperty = new Properties();
        sessionProperty.put("mail.smtp.auth", "true");
        sessionProperty.put("mail.smtp.starttls.enable", "true");
        sessionProperty.put("mail.smtp.host", "smtp.gmail.com");
        sessionProperty.put("mail.smtp.port", "587");


        Authenticator botAuth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yordantop1@gmail.com","cxti gidx slmm agnt");
            }
        };

        Session session = Session.getInstance(sessionProperty,botAuth);

        return session;
    }

}

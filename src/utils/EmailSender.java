package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;


import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String toEmail, String subject, String content) {
        final String username = "0aa3ebaae37eb6";     // ✅ Mailtrap username
        final String password = "3128929f593ef1";

        Properties props = new Properties();
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");  // TLS encryption

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);  // ✅ Full password here
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("no-reply@deli.app", "DELI-cious POS"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setText(content);

            Transport.send(msg);
            System.out.println("📧 Receipt sent to Mailtrap (sandbox inbox)");
        } catch (Exception e) {
            System.out.println("❌ Failed to send email: " + e.getMessage());
        }
    }
}

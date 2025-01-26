package Utils;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.*;

public class Mailer {
    public static void send(String from, String to, String cc, String subject, String body) {
        // Thông số kết nối tới Gmail
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");

        // Đăng nhập vào tài khoản Gmail
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = "";  //dien email
                String password = "";     //dien password 
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage mail = new MimeMessage(session);
            mail.setFrom(new InternetAddress(from));
            mail.setRecipients(Message.RecipientType.TO, to);
            if (cc != null && !cc.trim().isEmpty()) {
                mail.setRecipients(Message.RecipientType.CC, cc);
            }
            mail.setSubject(subject, "utf-8");
            mail.setText(body, "utf-8", "html");
            mail.setReplyTo(mail.getFrom());

            Transport.send(mail);
            System.out.println("Email đã được gửi thành công.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


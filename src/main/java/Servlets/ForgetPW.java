package Servlets;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import DAO.NhanVienDAO;
import Entity.NhanVien;

@WebServlet("/fgpass")
public class ForgetPW extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();

    public ForgetPW() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("quenmk.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        NhanVien nhanVien = nhanVienDAO.selectByEmail(email);
        Random rd = new Random();
        int x = rd.nextInt(1000000); // Consider generating a more secure password

        if (nhanVien == null) {
            request.setAttribute("errorMessage", "Email chưa được đăng ký");
            request.getRequestDispatcher("quenmk.jsp").forward(request, response);
            return;
        } else {
        	nhanVien.setPassword(String.valueOf(x));
			nhanVienDAO.updatemk(nhanVien);
            String subject = "Xác nhận khôi phục tài khoản Tin Tức 24h của bạn!";
            String message = createEmailMessage(x);

            String username = "petservices.psp@gmail.com";  
            String password = "jjxl uenz xbdl bgpy";    // Change this to your email password
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                MimeMessage mail = new MimeMessage(session);
                mail.setFrom(new InternetAddress(username));
                mail.setRecipients(Message.RecipientType.TO, email);
                mail.setSubject(subject);
                MimeMultipart multipart = new MimeMultipart();
                
                // Add email body
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(message, "text/html; charset=utf-8");
                multipart.addBodyPart(messageBodyPart);
                mail.setContent(multipart);

                Transport.send(mail);
                request.setAttribute("errorMessage", "Email khôi phục đã được gửi đến " + email);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Có lỗi xảy ra khi gửi email, vui lòng thử lại.");
            }
        }
        request.getRequestDispatcher("quenmk.jsp").forward(request, response);
    }

    private String createEmailMessage(int x) {
        return "<html>"
            + "<head>"
            + "<style>"
            + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }"
            + ".email-container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }"
            + ".header { text-align: center; padding-bottom: 20px; }"
            + ".header h1 { color: #2E86C1; }"
            + ".content { line-height: 1.6; }"
            + ".footer { text-align: center; padding-top: 20px; font-size: 0.9em; color: #777; }"
            + ".btn { display: inline-block; background-color: #2E86C1; color: #ffffff; padding: 10px 20px; text-decoration: none; border-radius: 5px; }"
            + ".btn:hover { background-color: #1A5F85; }"
            + "</style>"
            + "</head>"
            + "<body>"
            + "<div class='email-container'>"
            + "<div class='header'>"
            + "<h1>Xác Nhận Khôi Phục Tài Khoản</h1>"
            + "</div>"
            + "<div class='content'>"
            + "<p>Xin chào bạn,</p>"
            + "<p>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu của bạn.</p>"
            + "<p>Đây là mật khẩu mới của bạn: <strong style='color: #E74C3C; font-size: 1.2em;'>" + x + "</strong></p>"
            + "<p>Vui lòng đăng nhập và thay đổi mật khẩu này để bảo mật tài khoản của bạn.</p>"
            + "</div>"
            + "<div class='footer'>"
            + "<p>Trân trọng,</p>"
            + "<p>Đội ngũ hỗ trợ Tin Tức 24h</p>"
            + "</div>"
            + "</div>"
            + "</body>"
            + "</html>";
    }
}

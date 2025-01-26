package Servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.NewsLetterDAO;
import Entity.NewsLetter;

@WebServlet("/newsletterSubscription")
public class NewsletterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewsLetterDAO newsletterDAO = new NewsLetterDAO();

    public NewsletterServlet() {
        super();
    }
    @Override    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("docgia.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String email = request.getParameter("email");
        String actionType = request.getParameter("actionType");

        if (email != null && !email.isEmpty()) {
            if ("subscribe".equals(actionType)) {
                NewsLetter subscription = new NewsLetter();
                subscription.setEmail(email);
                subscription.setEnabled(true);
                newsletterDAO.insert(subscription);
                response.sendRedirect("http://localhost:8080/asm/docgia");

            } else if ("unsubscribe".equals(actionType)) {
                NewsLetter subscription = new NewsLetter();
                subscription.setEmail(email);
                subscription.setEnabled(false); 
                newsletterDAO.update(subscription);
                response.sendRedirect("http://localhost:8080/asm/docgia");
            }
        } else {
            response.sendRedirect("http://localhost:8080/asm/docgia");
        }
    }
}

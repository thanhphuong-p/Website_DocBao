package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import DAO.NewsDAO;
import Entity.News;

/**
 * Servlet implementation class NewsServlet
 */
@WebServlet("/news")
public class NewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewsDAO newsDAO = new NewsDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String categoryId = request.getParameter("categoryId");

        try {
            if (action == null && categoryId != null) {
                displayNewsByCategory(request, response, categoryId);
            } else {
                switch (action) {
                    case "top5hot":
                        displayTop5HotNews(request, response);
                        break;
                    case "top5latest":
                        displayTop5LatestNews(request, response);
                        break;
                    case "recent":
                        displayRecentNewsByUser(request, response);
                        break;
                    default:
                        response.sendRedirect("error.jsp");
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void displayNewsByCategory(HttpServletRequest request, HttpServletResponse response, String categoryId)
            throws ServletException, IOException {
        if (categoryId == null) categoryId = "lab1";  
        List<News> newsList = newsDAO.selectByCategoryId(categoryId); 
        request.setAttribute("newsList", newsList);
        request.getRequestDispatcher("trangtin.jsp").forward(request, response);
    }

    private void displayTop5HotNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<News> hotNews = newsDAO.getTop5HotNews();
        request.setAttribute("newsList", hotNews);
        request.getRequestDispatcher("docgia.jsp").forward(request, response);
    }

    private void displayTop5LatestNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<News> latestNews = newsDAO.getTop5LatestNews();
        request.setAttribute("newsList", latestNews);
        request.getRequestDispatcher("docgia.jsp").forward(request, response);
    }

    private void displayRecentNewsByUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String userId = request.getParameter("userId");
        List<News> recentNews = newsDAO.getRecentNewsByUser(userId);
        request.setAttribute("newsList", recentNews);
        request.getRequestDispatcher("docgia.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

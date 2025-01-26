package Servlets;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import Entity.Comment;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CommentDAO commentDAO = new CommentDAO();

    public String getCurrentDateAsString() {
        // Get the current date and time
        Date currentDate = new Date();
        
        // Format the date as a string in your desired format (e.g., "yyyy-MM-dd HH:mm:ss")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentDate);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String content = request.getParameter("content");
        String newsId = request.getParameter("newsId");
        String userId = request.getParameter("userId");

        // Tạo ID ngẫu nhiên cho bình luận
        String commentId = UUID.randomUUID().toString();

        // Lấy ngày hiện tại

        // Tạo đối tượng Comment mới
        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setNewsId(newsId);
        comment.setUserId(userId);
        comment.setContent(content);
        String currentDate = getCurrentDateAsString();
        comment.setCommentDate(currentDate);
        // Lưu bình luận vào cơ sở dữ liệu
        commentDAO.insert(comment);

        // Chuyển hướng về trang tin tức
        response.sendRedirect("bangtinchitiet?id=" + newsId);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported for this endpoint.");
    }

}

package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import jakarta.servlet.annotation.MultipartConfig;
import DAO.NewsDAO;
import Entity.News;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,  
	    maxFileSize = 1024 * 1024 * 10,      
	    maxRequestSize = 1024 * 1024 * 50
	)

@WebServlet({ 
    "/newss", 
    "/newss/edit/*", 
    "/newss/create", 
    "/newss/update",
    "/newss/delete", 
    "/newss/reset" 
})
public class ListNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewsDAO dao = new NewsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        
        if (path.contains("edit")) {
            String id = request.getPathInfo().substring(1); // Extract the ID from the URL
            News news = dao.selectById(id); // Retrieve the article by ID
            request.setAttribute("news", news); // Set the news object to the request
        }

        // Load articles for display
        List<News> articles = dao.selectAll();
        request.setAttribute("articles", articles);
        request.getRequestDispatcher("/quanlybaiviet.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String id = request.getParameter("id");
        News news = new News();
        news.setId(request.getParameter("id"));
        news.setTitle(request.getParameter("title"));
        news.setAuthor(request.getParameter("author"));
        news.setContent(request.getParameter("content"));
        news.setPostedDate(request.getParameter("postedDate"));
        news.setCategoryId(request.getParameter("categoryId"));
        news.setHome("1".equals(request.getParameter("home")));
        if (request.getPart("image") != null) {
            Part filePart = request.getPart("image"); // Get the uploaded file part
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Get the file name
            news.setImage(fileName); // Set the image name in the News object

            // Specify the upload directory
            
        }

        if (path.contains("edit")) {
			id = request.getPathInfo().substring(1);
			news = dao.selectById(id);
		} else if (path.contains("create")) {
			try {
				dao.insert(news);
				news = new News();
			} catch (RuntimeException e) {
				request.setAttribute("message","Trùng khóa chính");
			}
		} else if (path.contains("update")) {
			dao.update(news);
		} else if (path.contains("delete")) {
			dao.delete(news.getId());
			news = new News();
		} else {
			news = new News();
		}

        // Reload articles after processing
        List<News> articles = dao.selectAll();
        request.setAttribute("articles", articles);
        request.getRequestDispatcher("/quanlybaiviet.jsp").forward(request, response);
    }
}

package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DAO.CategoryDAO;
import DAO.NewsLetterDAO;
import Entity.Category;
import Entity.NewsLetter;

/**
 * Servlet implementation class ListNewsLetters
 */
@WebServlet({ 
    "/newsletters", 
    "/newsletters/edit/*", 
    "/newsletters/create", 
    "/newsletters/update",
    "/newsletters/delete", 
    "/newsletters/reset" 
})
public class ListNewsLetters extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsLetterDAO dao = new NewsLetterDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListNewsLetters() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();

	    if (path.contains("edit")) {
	        String id = request.getPathInfo().substring(1); // Extract the ID from the URL
	        NewsLetter newsletter = dao.selectById(id); // Retrieve the newsletter by ID
	        request.setAttribute("newsletters", newsletter); 
	    }

	    // Load newsletters for display
	    List<NewsLetter> newsletters = dao.selectAll();
	    request.setAttribute("newsletter", newsletters);
	    request.getRequestDispatcher("/quanlynewsletters.jsp").forward(request, response);
        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String path = request.getServletPath();
		    String email = request.getParameter("email");
		    NewsLetter newsletter = new NewsLetter();
		    newsletter.setEmail(email);
		    newsletter.setEnabled("1".equals(request.getParameter("gender")));

		    try {
		        if (path.contains("edit")) {
		            newsletter.setEmail(request.getParameter("email"));
		            dao.update(newsletter);
		        } else if (path.contains("create")) {
		            dao.insert(newsletter);
		        } else if (path.contains("delete")) {
		            dao.delete(email);
		        } else if (path.contains("update")) {
		        	
		        
		            dao.update(newsletter);
		        }else {
		        	newsletter = new NewsLetter();

		        }
		    } catch (RuntimeException e) {
		        request.setAttribute("message", "Error: " + e.getMessage());
		    }

		    List<NewsLetter> newsletters = dao.selectAll();
		    request.setAttribute("newsletter", newsletters);
		    request.getRequestDispatcher("/quanlynewsletters.jsp").forward(request, response);
	}
	}



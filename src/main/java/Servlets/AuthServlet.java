package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.NhanVienDAO;
import Entity.NhanVien;
import Utils.Auth;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/login")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 	String id = request.getParameter("id");
	        String password = request.getParameter("password");
	        NhanVien nhanVien = nhanVienDAO.selectById(id);
	        try {
	        if (nhanVien == null) {
	       
	        	request.setAttribute("errorMessage", "Sai tên đăng nhập");
	            request.getRequestDispatcher("login.jsp").forward(request, response);	        
	            } else {
	            boolean isAuthenticated = false;

	            if (nhanVien != null && password.equals(nhanVien.getPassword())) {
		        	System.out.print("Đăng nhập thành công!");
	                Auth.user = nhanVien;
	                isAuthenticated = true;

	                if (Auth.isLogin()) {
	                    if (!Auth.isAdmin()) {
	                        response.sendRedirect("admin.jsp");
	                    } else {
	                        response.sendRedirect("docgia");
	                    }
	                }
	            } else {
	            	
	            
	            }

	            if (!isAuthenticated) {
	            	request.setAttribute("errorMessage", "Sai mật khẩu");
	                request.getRequestDispatcher("login.jsp").forward(request, response);	            }

	}
	        }catch (Exception e) {
            	request.setAttribute("errorMessage", "Sai mật khẩu");

	        }

}
}

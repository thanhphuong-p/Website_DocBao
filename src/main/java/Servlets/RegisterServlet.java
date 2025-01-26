package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.NhanVienDAO;
import Entity.NhanVien;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String birthdayStr = request.getParameter("birthday");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender"); // Get gender as string
        String role = request.getParameter("role"); // Get role as string

        // Convert gender and role to boolean (BIT)
        boolean isMale = gender.equals("male");
        boolean isAdmin = role.equals("admin");

        // Parse the birthday (from String to Date)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthday = null;
        try {
            birthday = sdf.parse(birthdayStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Redirect back with an error message if date parsing fails
            request.setAttribute("errorMessage", "Invalid birthday format. Please use dd/MM/yyyy.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; // Exit the method if date parsing fails
        }

        // Create NhanVien object
        NhanVien nv = new NhanVien();
        nv.setFullname(fullname);
        nv.setId(id);
        nv.setPassword(password);
        nv.setEmail(email);
        nv.setBirthday(birthday); // Set birthday as Date object
        nv.setMobile(mobile);
        nv.setGender(isMale); // Set gender as BIT (1 for male, 0 for female)
        nv.setRole(isAdmin); // Set role as BIT (1 for admin, 0 for reader)

        try {
            // Save to database using NhanVienDAO
            nhanVienDAO.insert(nv); 
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}

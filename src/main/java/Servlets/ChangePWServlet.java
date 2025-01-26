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
 * Servlet implementation class ChangePWServlet
 */
@WebServlet("/changepw")
public class ChangePWServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();

    public ChangePWServlet() {
        super();
    }

    /**
     * Handles GET requests to load the password change page.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("changepw.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to change the password.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("userId");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        NhanVien nhanVien = nhanVienDAO.selectById(id);

        try {
            // Validate if the current user is authenticated
            if (Auth.user == null || !id.equals(Auth.user.getId())) {
                request.setAttribute("errorMessage", "Tài khoản không đúng");
                request.getRequestDispatcher("changepw.jsp").forward(request, response);
                return;
            }

            if (!currentPassword.equals(Auth.user.getPassword())) {
                request.setAttribute("errorMessage", "Sai mật khẩu hiện tại.");
                request.getRequestDispatcher("changepw.jsp").forward(request, response);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("errorMessage", "Xác nhận mật khẩu không khớp.");
                request.getRequestDispatcher("changepw.jsp").forward(request, response);
                return;
            }

            Auth.user.setPassword(newPassword);
            nhanVienDAO.updatemk(Auth.user);

            request.setAttribute("successMessage", "Đổi mật khẩu thành công. Vui lòng đăng nhập lại.");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi đổi mật khẩu. Vui lòng thử lại.");
            request.getRequestDispatcher("changepw.jsp").forward(request, response);
            e.printStackTrace();
        }
    }
}

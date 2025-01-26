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
import DAO.NhanVienDAO;
import Entity.NhanVien;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  
    maxFileSize = 1024 * 1024 * 10,      
    maxRequestSize = 1024 * 1024 * 50
)

@WebServlet({
    "/users",
    "/users/edit/*",
    "/users/create",
    "/users/update",
    "/users/delete",
    "/users/reset"
})
public class ListUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NhanVienDAO dao = new NhanVienDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.contains("edit")) {
            String id = request.getPathInfo().substring(1); // Extract the ID from the URL
            NhanVien nv = dao.selectById(id); // Retrieve the user by ID
            request.setAttribute("nv", nv); // Set the user object to the request
        }

        // Load users for display
        List<NhanVien> users = dao.selectAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/quanlyusers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String id = request.getParameter("id");
        NhanVien nv = new NhanVien();

        nv.setId(request.getParameter("id"));
        nv.setFullname(request.getParameter("fullname"));
        nv.setPassword(request.getParameter("password"));
        nv.setEmail(request.getParameter("email"));
        nv.setMobile(request.getParameter("mobile"));
        nv.setGender("1".equals(request.getParameter("gender")));
        nv.setRole("1".equals(request.getParameter("role")));
        nv.setBirthday(java.sql.Date.valueOf(request.getParameter("birthday"))); 

        if (path.contains("edit")) {
            id = request.getPathInfo().substring(1);
            nv = dao.selectById(id);
        } else if (path.contains("create")) {
            try {
                dao.insert(nv);
                nv = new NhanVien();
            } catch (RuntimeException e) {
                request.setAttribute("message", "Primary key conflict.");
            }
        } else if (path.contains("update")) {
            dao.update(nv);
        } else if (path.contains("delete")) {
            dao.delete(nv.getId());
            nv = new NhanVien();
        } else {
            nv = new NhanVien();
        }

        // Reload users after processing
        List<NhanVien> users = dao.selectAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/quanlyusers.jsp").forward(request, response);
    }
}

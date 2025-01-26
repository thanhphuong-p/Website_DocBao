package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import DAO.CategoryDAO; 
import Entity.Category; 

@WebServlet({ 
    "/categories", 
    "/categories/edit/*", 
    "/categories/create", 
    "/categories/update",
    "/categories/delete", 
    "/categories/reset" 
})
public class ListCategoriesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDAO dao = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.contains("edit")) {
            String id = request.getPathInfo().substring(1); 
            Category category = dao.selectById(id); 
            request.setAttribute("category", category); 
        }

        // Load categories for display
        List<Category> categories = dao.selectAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/quanlycategory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String id = request.getParameter("id");
        Category category = new Category();
        category.setId(request.getParameter("id"));
        category.setName(request.getParameter("name"));
try {
        if (path.contains("edit")) {
            id = request.getPathInfo().substring(1);
            category = dao.selectById(id);
        } else if (path.contains("create")) {
            try {
                dao.insert(category);
                category = new Category();
            } catch (RuntimeException e) {
                request.setAttribute("message", "Trùng khóa chính");
            }
        } else if (path.contains("update")) {
            dao.update(category);
        } else if (path.contains("delete")) {
            dao.delete(category.getId());
            category = new Category();
        } else {
            category = new Category();
        }
}catch (RuntimeException e) {
    System.out.print("lỗi"+e);
}
        List<Category> categories = dao.selectAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/quanlycategory.jsp").forward(request, response);
    }
}

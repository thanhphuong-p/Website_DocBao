package Servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.ThongKeDAO;
import Entity.NewsStatistics;

@WebServlet("/newsletterStatistics")
public class StatisticsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ThongKeDAO newsDao = new ThongKeDAO();

    public StatisticsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("thongke.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        List<NewsStatistics> statistics;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date startDate = null;
            Date endDate = null;
            
            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDate = sdf.parse(startDateStr);
            }
            
            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDate = sdf.parse(endDateStr);
            }

            // Kiểm tra ngày
            if (startDate != null && endDate != null && startDate.after(endDate)) {
                request.setAttribute("message", "Ngày bắt đầu không thể sau ngày kết thúc.");
                request.getRequestDispatcher("thongke.jsp").forward(request, response);
                return;
            }

            String bd = startDate != null ? new SimpleDateFormat("yyyy-MM-dd").format(startDate) : null;
            String kt = endDate != null ? new SimpleDateFormat("yyyy-MM-dd").format(endDate) : null;

            statistics = newsDao.getNewsStatistics(bd, kt);
            request.setAttribute("statistics", statistics);
            request.getRequestDispatcher("thongke.jsp").forward(request, response);

        } catch (ParseException e) {
            request.setAttribute("message", "Lỗi định dạng ngày tháng. Vui lòng sử dụng định dạng dd/MM/yyyy.");
            request.getRequestDispatcher("thongke.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Có lỗi xảy ra trong quá trình lấy thống kê.");
            request.getRequestDispatcher("thongke.jsp").forward(request, response);
        }
    }

}

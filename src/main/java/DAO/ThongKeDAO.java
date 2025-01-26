package DAO;

import Utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Entity.NewsStatistics;

public class ThongKeDAO {

    private List<NewsStatistics> getListOfNewsStatistic(String sql, Object... args) {
        try {
            List<NewsStatistics> list = new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                String newsId = rs.getString("NewsId");
                String title = rs.getString("Title");
                int totalViews = rs.getInt("TotalViews");
                int totalComments = rs.getInt("TotalComments");

                NewsStatistics statistic = new NewsStatistics(newsId, title, totalViews, totalComments);
                list.add(statistic);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<NewsStatistics> getNewsStatistics(String bd, String kt) {
        String sql = "EXEC GetNewsStatistics ?,?;";
        return this.getListOfNewsStatistic(sql, bd, kt);
    }
}

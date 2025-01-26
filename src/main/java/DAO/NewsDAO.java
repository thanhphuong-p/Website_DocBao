package DAO;

import Utils.XJdbc;
import Entity.News;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO extends Java3AsmDAO<News, String> {

    public void insert(News model) {
        String sql = "INSERT INTO NEWS (Id, Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        XJdbc.update(sql,
                model.getId(),
                model.getTitle(),
                model.getContent(),
                model.getImage(),
                model.getPostedDate(),
                model.getAuthor(),
                model.getViewCount(),
                model.getCategoryId(),
                model.isHome());
    }

    public void update(News model) {
        String sql = "UPDATE NEWS SET Title = ?, Content = ?, Image = ?, PostedDate = ?, Author = ?, ViewCount = ?, CategoryId = ?, Home = ? WHERE Id = ?;";
        XJdbc.update(sql,
                model.getTitle(),
                model.getContent(),
                model.getImage(),
                model.getPostedDate(),
                model.getAuthor(),
                model.getViewCount(),
                model.getCategoryId(),
                model.isHome(),
                model.getId());
    }

    public void delete(String newsId) {
        String sql = "DELETE FROM NEWS WHERE Id = ?;";
        XJdbc.update(sql, newsId);
    }

    public List<News> selectAll() {
        String sql = "SELECT * FROM NEWS";
        return this.selectBySql(sql);
    }

    public News selectById(String newsId) {
        String sql = "SELECT * FROM NEWS WHERE Id = ?;";
        List<News> list = this.selectBySql(sql, newsId);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<News> selectByCategoryId(String categoryId) {
        String sql = "SELECT * FROM NEWS WHERE CategoryId = ?;";
        return this.selectBySql(sql, categoryId);
    }
    
    public List<News> getTop5HotNews() {
        String sql = "EXEC GetTop5HotNews;";
        return this.selectBySql(sql);
    }

    public List<News> getTop5LatestNews() {
        String sql = "EXEC GetTop5LatestNews;";
        return this.selectBySql(sql);
    }

    public List<News> getRecentNewsByUser(String userId) {
        String sql = "EXEC GetRecentNewsByUser @UserId = ?";
        return this.selectBySql(sql, userId);
    }

    protected List<News> selectBySql(String sql, Object... args) {
        List<News> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                News entity = new News();
                entity.setId(rs.getString("Id"));
                entity.setTitle(rs.getString("Title"));
                entity.setContent(rs.getString("Content"));
                entity.setImage(rs.getString("Image"));
                entity.setPostedDate(rs.getString("PostedDate"));
                entity.setAuthor(rs.getString("Author"));
                entity.setViewCount(rs.getInt("ViewCount"));
                entity.setCategoryId(rs.getString("CategoryId"));
                entity.setHome(rs.getBoolean("Home"));
                list.add(entity);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Consider logging this instead
            throw new RuntimeException(ex);
        }
        return list;
    }
}

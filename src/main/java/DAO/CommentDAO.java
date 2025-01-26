package DAO;

import Utils.XJdbc;
import Entity.Comment;
import Entity.News;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends Java3AsmDAO<Comment, String> {

    public void insert(Comment model) {
        String sql = "INSERT INTO CATEGORIES (Id, Name) VALUES (?, ?)";  
        XJdbc.update(sql,
                model.getId()
        ); 
    }

    public void update(Comment model) {
        String sql = "UPDATE CATEGORIES SET Name = ? WHERE Id = ?";  
        XJdbc.update(sql,
                model.getId()
        );
    }

    public void delete(String id) {
        String sql = "DELETE FROM CATEGORIES WHERE Id = ?";  // Updated to the correct table name
        XJdbc.update(sql, id);
    }

    public List<Comment> selectAll() {
        String sql = "SELECT * FROM CATEGORIES";  // Updated to select from the correct table
        return this.selectBySql(sql);
    }

    public Comment selectById(String id) {  // Changed Id to id for consistency
        String sql = "SELECT c.Content, c.CommentDate, u.id FROM COMMENTS c JOIN USERS u ON c.UserId = u.Id WHERE c.NewsId = ?;";  // Updated to the correct table name
        List<Comment> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    public List<Comment> selectByCommentId(String categoryId) {
        String sql = "SELECT c.Content, c.CommentDate, u.id FROM COMMENTS c JOIN USERS u ON c.UserId = u.Id WHERE c.NewsId = ?;";  // Updated to the correct table name
        return this.selectBySql(sql, categoryId);
    }

    protected List<Comment> selectBySql(String sql, Object... args) {
        List<Comment> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                	Comment entity = new Comment();  // Changed from NhanVien to Category
                	entity.setContent(rs.getString("Content"));
                	entity.setCommentDate(rs.getString("CommentDate"));
                	entity.setId(rs.getString("Id"));  // Assuming Category has a 'Name' field
                    list.add(entity);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
}

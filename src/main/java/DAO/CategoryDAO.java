package DAO;

import Utils.XJdbc;
import Entity.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends Java3AsmDAO<Category, String> {

    public void insert(Category model) {
        String sql = "INSERT INTO CATEGORIES (Id, Name) VALUES (?, ?)";  
        XJdbc.update(sql,
                model.getId(),
                model.getName()
        ); 
    }

    public void update(Category model) {
        String sql = "UPDATE CATEGORIES SET Name = ? WHERE Id = ?";  
        XJdbc.update(sql,
                model.getName(),  
                model.getId()
        );
    }

    public void delete(String id) {
        String sql = "DELETE FROM CATEGORIES WHERE Id = ?";  // Updated to the correct table name
        XJdbc.update(sql, id);
    }

    public List<Category> selectAll() {
        String sql = "SELECT * FROM CATEGORIES";  // Updated to select from the correct table
        return this.selectBySql(sql);
    }

    public Category selectById(String id) {  // Changed Id to id for consistency
        String sql = "SELECT * FROM CATEGORIES WHERE Id=?";  // Updated to the correct table name
        List<Category> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<Category> selectBySql(String sql, Object... args) {
        List<Category> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    Category entity = new Category();  // Changed from NhanVien to Category
                    entity.setId(rs.getString("Id"));
                    entity.setName(rs.getString("Name"));  // Assuming Category has a 'Name' field
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

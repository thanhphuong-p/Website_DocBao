package DAO;

import Utils.XJdbc;
import Entity.NewsLetter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsLetterDAO extends Java3AsmDAO<NewsLetter, String> {

    public void insert(NewsLetter model) {
        String sql = "INSERT INTO NEWSLETTERS (Email, Enabled) VALUES (?, ?)";  
        XJdbc.update(sql,
                model.getEmail(),
                model.isEnabled()
        ); 
    }

    public void update(NewsLetter model) {
        String sql = "UPDATE NEWSLETTERS SET Enabled = ?  WHERE Email = ?";  
        XJdbc.update(sql,
        		model.isEnabled(),
        		model.getEmail()
                
        		
        		);
    }

    public void delete(String id) {
        String sql = "DELETE FROM NEWSLETTERS WHERE Email = ?";  // Updated to the correct table name
        XJdbc.update(sql, id);
    }

    public List<NewsLetter> selectAll() {
        String sql = "SELECT * FROM NEWSLETTERS";  // Updated to select from the correct table
        return this.selectBySql(sql);
    }

    public NewsLetter selectById(String id) {  // Changed Id to id for consistency
        String sql = "SELECT * FROM NEWSLETTERS WHERE Email=?";  // Updated to the correct table name
        List<NewsLetter> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<NewsLetter> selectBySql(String sql, Object... args) {
        List<NewsLetter> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                	NewsLetter entity = new NewsLetter();  
                    entity.setEmail(rs.getString("Email"));
                    entity.setEnabled(rs.getBoolean("Enabled"));
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

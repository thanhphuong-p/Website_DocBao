package DAO;

import Utils.XJdbc;
import Entity.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends Java3AsmDAO<NhanVien, String> {

    public void insert(NhanVien model) {
        String sql = "INSERT INTO USERS (Id, Password, Fullname, Birthday, Gender, Mobile, Email, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        XJdbc.update(sql,
                model.getId(),
                model.getPassword(),
                model.getFullname(),
                model.getBirthday(),
                model.isGender(),
                model.getMobile(),
                model.getEmail(),
                model.isRole()
        );
    }

    public void update(NhanVien model) {
        String sql = "UPDATE USERS SET Password = ?, Fullname = ?, Birthday = ?, Email = ?, Mobile = ?, Role = ? WHERE Id = ?;";
        XJdbc.update(sql,
                model.getPassword(),
                model.getFullname(),
                model.getBirthday(),
                model.getEmail(),
                model.getMobile(),
                model.isRole(),
                model.getId()
        );
    }

    public void updatemk(NhanVien model) {
        String sql = "UPDATE USERS SET Password = ? WHERE Email = ?;";
        XJdbc.update(sql,
                model.getPassword(),
                model.getEmail()
        );
    }

    public void delete(String Id) {
        String sql = "DELETE FROM USERS WHERE Id = ?;";
        XJdbc.update(sql, Id);
    }
    public void quenmk(String Id) {
        String sql = "UPDATE USERS SET Password = ? WHERE Email = ?;";
        XJdbc.update(sql, Id);
    }

    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM USERS";
        return this.selectBySql(sql);
    }

    public NhanVien selectById(String Id) {
        String sql = "SELECT * FROM USERS WHERE Id=?";
        List<NhanVien> list = this.selectBySql(sql, Id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public NhanVien selectByEmail(String email) {
        String sql = "SELECT * FROM USERS WHERE Email=?";
        List<NhanVien> list = this.selectBySql(sql, email);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setId(rs.getString("Id"));
                    entity.setPassword(rs.getString("Password"));
                    entity.setFullname(rs.getString("Fullname"));
                    entity.setRole(rs.getBoolean("Role"));
                    entity.setGender(rs.getBoolean("Gender"));
                    entity.setBirthday(rs.getDate("Birthday"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setMobile(rs.getString("Mobile"));
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

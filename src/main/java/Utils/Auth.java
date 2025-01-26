package Utils;

import Entity.NhanVien;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Auth {

    public static NhanVien user = null;
    private static NhanVien currentNhanVien;


    public static void clear() {
        Auth.user = null;
        
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }
    public static boolean isAdmin() {
        return Auth.isLogin() && user.isRole();
    }
    public static String getNhanVienName() {
        if (user instanceof NhanVien) {
            NhanVien emp = (NhanVien) user;
            return emp.getFullname();
        
    }
        return "";
    }
     public static String getMaNV() {
        if (user instanceof NhanVien) {
            NhanVien emp = (NhanVien) user;
            return emp.getId();
        }
        return "";
    }
     public static String getNgaySinh() {
    if (user instanceof NhanVien) {
        NhanVien emp = (NhanVien) user;
        Date ngaySinh = emp.getBirthday();
        if (ngaySinh != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Or any desired date format
            return sdf.format(ngaySinh);
        }
    }
    return "";
}
     public static String getGioiTinh() {
    if (user instanceof NhanVien) {
        NhanVien emp = (NhanVien) user;
            return emp.isGender()?"Nữ":"Nam";
    }
    return "";
}
     public static String getSDT() {
    if (user instanceof NhanVien) {
        NhanVien emp = (NhanVien) user;
        String sdt = emp.getMobile();
        return String.valueOf(sdt);
    }
    return "";
}
     public static String getEmail() {
        if (user instanceof NhanVien) {
            NhanVien emp = (NhanVien) user;
            return emp.getEmail();
        }
        return "";
    }
     public static String getDiaChi() {
        if (user instanceof NhanVien) {
            NhanVien emp = (NhanVien) user;
            return emp.getEmail();
        }
        return "";
    }

}

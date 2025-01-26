<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="Entity.NhanVien" %>
<%@ page import="Utils.Auth" %>
    
<header style="display: flex; align-items: center; justify-content: center; background-color: #333; padding: 10px 20px; position: relative;">
    <div style="position: absolute; left: 20px; display: flex; align-items: center;">
        <img src="images/logoshop.png" alt="Logo" style="width: 30px; height: auto; margin-right: 20px;" />
        <a href="docgia.jsp" style="color: white; text-decoration: none; font-size: 18px; padding: 10px;">Home</a>
    </div>
    <h1 style="color: white; margin: 0; font-size: 24px;">Tin Tức 24h</h1>
</header>

    
    <nav>
        <ul>
            <li><a href="docgia">Trang chủ</a></li>
            <li><a href="#" onclick="loadContent('news?categoryId=lab2')">Sức khỏe</a></li>
<li><a href="#" onclick="loadContent('news?categoryId=lab10')">Chính trị</a></li>
<li><a href="#" onclick="loadContent('news?categoryId=lab9')">Thể thao</a></li>

             <% 
                        NhanVien user = Auth.user; 
                        if (user == null) { 
                    %>
            <li><a href="login.jsp">Đăng Nhập</a></li>
                        <li><a href="register.jsp">Đăng Ký</a></li>
               <% 
                        } else { 
                    %>
                        <li><a href="#" onclick="loadContent('detailadmin.jsp')">Tài khoản</a></li>
                        <li><a href="dangxuat.jsp">Đăng xuất</a></li>  
                    <% 
                        } 
                    %>  
        </ul>
    </nav>
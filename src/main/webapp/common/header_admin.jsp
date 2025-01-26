<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="Entity.NhanVien" %>
<%@ page import="Utils.Auth" %>
    
<header style="display: flex; align-items: center; justify-content: center; background-color: #333; padding: 10px 20px; position: relative;">
    <div style="position: absolute; left: 20px; display: flex; align-items: center;">
        <img src="images/logoshop.png" alt="Logo" style="width: 30px; height: auto; margin-right: 20px;" />
        <a href="admin.jsp" style="color: white; text-decoration: none; font-size: 18px; padding: 10px;">Home</a>
    </div>
    <h1 style="color: white; margin: 0; font-size: 24px;"></h1>
</header>
    <nav>
        <ul>
        	<li><a href="http://localhost:8080/asm/admin.jsp" >Trang chủ</a></li>		
            <li><a href="http://localhost:8080/asm/newss">Quản lý bài viết</a></li>
            <li><a href="http://localhost:8080/asm/categories">Loại tin tức</a></li>
            <li><a href="http://localhost:8080/asm/users">Người dùng</a></li>
            <li><a href="http://localhost:8080/asm/newsletters">Newsletter</a></li>
            <li><a href="http://localhost:8080/asm/newsletterStatistics">Thống kê</a></li>
            <% 
                        NhanVien user = Auth.user; 
                        if (user == null) { 
                    %>
            <li><a href="http://localhost:8080/asm/login.jsp">Đăng Nhập</a></li>
                        <li><a href="http://localhost:8080/asm/register.jsp">Đăng Ký</a></li>
               <% 
                        } else { 
                    %>
                        <li><a href="#" onclick="loadContent('http://localhost:8080/asm/detailadmin.jsp')">Tài khoản</a></li>
                        <li><a href="http://localhost:8080/asm/logout.jsp">Đăng xuất</a></li>  
                    <% 
                        } 
                    %>          
        </ul>
    </nav>
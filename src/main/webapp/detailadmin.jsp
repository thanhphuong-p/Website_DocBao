<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Utils.Auth" %>
    <%@ page import="Entity.NhanVien" %>

<%
    // Assume you have an authenticated user
    NhanVien currentUser = new NhanVien();


%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản trị và Phóng viên</title>
    <style>
        body {
            font-family: 'Helvetica Neue', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        .header {
            background-color: #2c3e50;
            color: white;
            padding: 0.1px 0;
            text-align: center;
            width: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
        }
        .header h2 {
            margin: 0;
            margin-bottom: 20px;
        }
        nav {
            background-color: #ecf0f1;
            padding: 10px 0;
            margin-top: 0px;
            width: 100%;
             
        }
        nav ul {
            list-style: none;
            display: flex;
            justify-content: center;
            padding: 0; 
            margin: 0; 
        }
        nav ul li {
            margin: 0 15px;
        }
        nav ul li a {
            text-decoration: none;
            color: #2980b9;
            font-weight: bold;
        }
        nav ul li a:hover {
            color: #f1c40f;
        }
        .content {
            padding: 20px;
            padding-top: 10px; 
        }
        .footer {
            background-color: #2c3e50;
            color: white;
            padding: 10px 0;
            text-align: center;
            width: 100%;
            bottom: 0;
            left: 0;
        }
        h1 {
            color: white;
        }
        .card {
            background-color: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .card h2 {
            margin-bottom: 15px;
            color: #2c3e50;
        }
        .card p {
            margin-bottom: 15px;
            font-size: 16px;
            color: #7f8c8d;
        }
        .card button {
            padding: 10px 20px;
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .card button:hover {
            background-color: #3498db;
        }
    </style>
</head>
<body>

    

        <h2>Xin chào!</h2>

        <div class="card">
            <h2>Thông tin tài khoản</h2>
            <p>Mã nhân viên: <%= Auth.getMaNV() %></p>                    
            <p>Tên đầy đủ: <%= Auth.getNhanVienName() %></p>        
            <p>Ngày sinh: <%= Auth.getNgaySinh() %></p>
            <p>Giới tính: <%= Auth.getGioiTinh() %></p>
            <p>Email: <%= Auth.getEmail() %></p>
            <p>Số điện thoại: <%= Auth.getSDT() %></p>    
<button onclick="window.location.href='changepw.jsp'">Đổi mật khẩu</button>
                      
        </div>




</body>
</html>

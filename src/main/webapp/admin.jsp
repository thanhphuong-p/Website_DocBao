<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="Entity.NhanVien" %>
<%@ page import="Utils.Auth" %>
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
         header{
            background-color: #2c3e50;
            color: white;
            padding: 2px 0;
            text-align: center;
            
        }
         footer {
            background-color: #2c3e50;
            color: white;
            padding: 8px 0;
            text-align: center;
            
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
    <%@ include file = "/common/header_admin.jsp" %>

    <div class="content" id = "content">
        <h2>Xin chào, Admin/Phóng Viên!</h2>

        <div class="card">
            <h2>Quản lý bài viết</h2>
            <p>Tạo, chỉnh sửa và xóa bài viết trên hệ thống.</p>
<button onclick="location.href='http://localhost:8080/asm/newss'">Tạo bài viết mới</button>
        </div>

        <div class="card">
            <h2>Quản lý loại tin tức</h2>
            <p>Thêm hoặc sửa đổi các loại tin tức như Văn hóa, Thể thao, Pháp luật...</p>
            <button onclick="location.href='http://localhost:8080/asm/categories'" >Quản lý loại tin</button>
        </div>

        <div class="card">
            <h2>Quản lý người dùng</h2>
            <p>Quản lý tài khoản của người dùng, bao gồm phân quyền cho admin và phóng viên.</p>
            <button onclick="location.href='http://localhost:8080/asm/users'">Quản lý người dùng</button>
        </div>

        <div class="card">
            <h2>Newsletter</h2>
            <p>Quản lý và gửi các bản tin đến người dùng đăng ký nhận thông báo.</p>
            <button onclick="location.href='http://localhost:8080/asm/newsletters'">Quản lý Newsletter</button>
        </div>

        <div class="card">
            <h2>Thống kê</h2>
            <p>Xem thống kê về số lượng người dùng, bài viết và tương tác trên hệ thống.</p>
            <button onclick="location.href='http://localhost:8080/asm/newsletterStatistics'">Xem thống kê</button>
        </div>
    </div>

    <%@include file = "/common/footer_admin.jsp" %>


</body>
<script>
function loadContent(file) {
    fetch(file)
        .then(response => response.text())
        .then(data => {
            document.getElementById("content").innerHTML = data;
        })
        .catch(error => console.error('Error loading content:', error));
}
</script>
</html>

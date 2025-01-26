<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="Entity.NewsStatistics" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thống Kê Bài Viết</title>
    <style>
        body {
            font-family: 'Helvetica Neue', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        h2 {
            color: #2c3e50;
        }
        button {
            padding: 10px 15px;
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin: 5px;
        }
        button:hover {
            background-color: #3498db;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #ecf0f1;
            color: #2c3e50;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        #add-category-form {
            background-color: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            margin-top: 20px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            color: #2c3e50;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #bdc3c7;
            border-radius: 5px;
            margin-bottom: 15px;
        }
         input[type="submit"]{
            padding: 10px 20px;
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        textarea {
            height: 100px;
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
        header {
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

    <%@ include file="/common/header_admin.jsp" %>

    <h2>Thống Kê Bài Viết</h2>
    
    <!-- Hiển thị thông báo lỗi hoặc thành công -->
    <c:if test="${not empty message}">
        <div style="color: red;">${message}</div>
    </c:if>

    <div class="card">
        <form method="post">
            <label for="startDate">Ngày Bắt Đầu:</label>
            <input type="text" id="startDate" name="startDate">
            <br>
            <label for="endDate">Ngày Kết Thúc:</label>
            <input type="text" id="endDate" name="endDate">
            <br>
            <input type="submit" value="Lấy Thống Kê">
            <button onclick="location.href='http://localhost:8080/asm/newsletterStatistics'">Reset</button>
        </form>

            <table>
                <thead>
                    <tr>
                        <th>ID Bài Viết</th>
                        <th>Tiêu Đề</th>
                        <th>Tổng Lượt Xem</th>
                        <th>Tổng Bình Luận</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="statistic" items="${statistics}">
                        <tr>
                            <td>${statistic.newsId}</td> 
                            <td>${statistic.title}</td> <!-- Title -->
                            <td>${statistic.totalViews}</td> <!-- TotalViews -->
                            <td>${statistic.totalComments}</td> <!-- TotalComments -->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        
        <c:if test="${empty statistics}">
            <p>Không có dữ liệu cho khoảng thời gian này.</p>
        </c:if>
    </div>

    <%@ include file="/common/footer_admin.jsp" %>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Category" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Danh Mục</title>
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

    <h2>Quản lý Danh Mục</h2>
    
    <!-- Display error or success messages -->
    <c:if test="${not empty message}">
        <div style="color: red;">${message}</div>
    </c:if>

    <div class="card">

        <form method="post">
            <label for="id">ID:</label>
            <input type="text" id="id" name="id" value="${category != null ? category.id : ''}" required>

            <label for="name">Tên Danh Mục:</label>
            <input type="text" id="name" name="name" value="${category != null ? category.name : ''}" required>

            <br>

            <c:choose>
                <c:when test="${category == null}">
                    <button formaction="${path}/asm/categories/create">Tạo mới</button>
                </c:when>
                <c:otherwise>
                    <button formaction="${path}/asm/categories/update">Cập nhật</button>
                    <button formaction="${path}/asm/categories/delete" 
                            onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</button>
                </c:otherwise>
            </c:choose>
            <button formaction="${path}/asm/categories/reset">Reset</button>
        </form>

        <!-- TABLE -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Danh Mục</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${categories}">
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>
                        <td>
                            <a href="${path}/asm/categories/edit/${category.id}">Sửa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="/common/footer_admin.jsp" %>

</body>
</html>

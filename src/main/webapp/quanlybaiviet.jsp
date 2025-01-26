<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.News" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý bài viết</title>
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
        #add-article-form {
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
        input[type="text"], input[type="file"], textarea {
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
	
    <h2>Quản lý Bài Viết</h2>
        <div class="card">

    
    <!-- FORM -->
    <form method="post" enctype="multipart/form-data">
        <label for="id">ID:</label>
		<input type="text" id="id" name="id" value="${news != null ? news.id : ''}" required>

        <label for="title">Tiêu đề:</label>
        <input type="text" id="title" name="title" value="${news != null ? news.title : ''}" required>

        <label for="author">Tác giả:</label>
        <input type="text" id="author" name="author" value="${news != null ? news.author : ''}" required>

        <label for="content">Nội dung:</label>
        <textarea id="content" name="content" required>${news != null ? news.content : ''}</textarea>

        <label for="postedDate">Ngày đăng:</label>
        <input type="text" id="postedDate" name="postedDate" value="${news != null ? news.postedDate : ''}">
		
		<label for="image">Hình ảnh:</label>
            <input type="file" id="image" name="image" >
            <c:if test="${news != null && news.image != null}">
    <p>Tên hình ảnh: ${news.image}</p>
</c:if>
		
        <label for="categoryId">Loại tin:</label>
        <input type="text" id="categoryId" name="categoryId" value="${news != null ? news.categoryId : ''}" required>

        <label for="home">Hiển thị trên trang chính:</label>
        <input type="checkbox" id="home" name="home" value="1" ${news != null && news.home ? 'checked' : ''}>
        
        <c:choose>
                <c:when test="${news == null}">
                    <button formaction="${path}/asm/newss/create">Tạo mới</button>
                </c:when>
                <c:otherwise>
                    <button formaction="${path}/asm/newss/update">Cập nhật</button>
                    <button formaction="${path}/asm/newss/delete" 
                            onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</button>
                </c:otherwise>
            </c:choose>
            <button formaction="${path}/asm/newss/reset">Reset</button>
    </form>

    <!-- TABLE -->
    <table>
        <thead>
            <tr>
                <th>Tiêu đề</th>
                <th>Tác giả</th>
                <th>Ngày đăng</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="article" items="${articles}">
                <tr>
                    <td>${article.title}</td>
                    <td>${article.author}</td>
                    <td>${article.postedDate}</td>
                    <td>
				<a href="${path}/asm/newss/edit/${article.getId()}">Sửa</a>
                    
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        
    </table>
            </div>
        <%@include file = "/common/footer_admin.jsp" %>
    
</body>
</html>

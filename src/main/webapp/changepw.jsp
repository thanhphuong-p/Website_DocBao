<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>Đổi mật khẩu</title>
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
            padding: 10px 0;
            text-align: center;
            width: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
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
        .card {
            background-color: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            margin-bottom: 20px;
            max-width: 400px;
            margin: 0 auto;
        }
        .card h2 {
            margin-bottom: 15px;
            color: #2c3e50;
        }
        .card input[type="text"], .card input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .card input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .card input[type="submit"]:hover {
            background-color: #3498db;
        }
        .error-message {
            color: red;
            margin-bottom: 10px;
        }
        .register-link {
            display: block;
            margin-top: 10px;
            color: #2980b9;
            text-align: center;
        }
        .register-link:hover {
            color: #f1c40f;
        }
    </style>
</head>
<body>


    <div class="content">
        <div class="card">
            <form action="changepw" method="post">
        		<div class="error-message">${errorMessage != null ? errorMessage : ""}</div>
                <input type="text" name="userId" value="<%= Auth.getMaNV() %>"  placeholder="ID User" required readonly>
                <input type="password" name="currentPassword" placeholder="Current Password" required>
                <input type="password" name="newPassword" placeholder="New Password" required>
                <input type="password" name="confirmPassword" placeholder="Confirm New Password" required>
                <input type="submit" value="Change Password">
                <a class="register-link" href="register.jsp">Register</a>
                <a class="register-link" href="forgotpw.jsp">Forgot Password?</a>
            </form>
        </div>
    </div>
</body>
</html>

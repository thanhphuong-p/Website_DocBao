<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Utils.Auth" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Xuất</title>
</head>
<body>
<%

    
    Auth.user = null; 
    response.sendRedirect("docgia");
%>
</body>
</html>

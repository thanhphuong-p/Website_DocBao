<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.News" %>
<%@ page import="Utils.Auth" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Độc Giả</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Helvetica Neue', sans-serif;
            color: #333;
        }
        header, footer {
            background-color: #2c3e50;
            color: white;
            padding: 15px 0;
            text-align: center;
            
        }
        
        nav ul {
            list-style: none;
            display: flex;
            justify-content: center;
            background-color: #ecf0f1;
            padding: 15px;
              width: 100%;
        }
        nav ul li {
            margin: 0 15px;
        }
        nav ul li a {
            text-decoration: none;
            color: #2980b9;
            font-weight: bold;
        }
        .main-content {
            display: flex;
            justify-content: space-between;
            margin: 20px;
        }
        .content {
            flex: 3;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .sidebar {
            flex: 1;
            margin-left: 20px;
        }
        .sidebar h3 {
            background-color: #f39c12;
            color: white;
            padding: 10px;
            margin-bottom: 10px;
        }
        .sidebar form input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        footer p {
            margin-top: 10px;
        }
        nav ul li a:hover {
            color: #f1c40f;
        }
        .news-item {
        display: flex;
        background-color: #fff;
        margin-bottom: 20px;
        padding: 15px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        transition: all 0.3s;
        cursor: pointer;
    }
    .news-item:hover {
        transform: translateY(-5px);
    }
    .news-item img {
        width: 120px;
        height: 100px;
        background-color: #3498db;
        margin-right: 20px;
        border-radius: 5px;
    }
    .news-item h3 {
        color: #2980b9;
        margin-bottom: 10px;
    }
    .news-item p {
        font-size: 14px;
        margin-bottom: 10px;
    }
    .news-item span {
        font-size: 12px;
        color: #7f8c8d;
    }
    button {
            padding: 10px 20px;
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <%@ include file = "/common/header.jsp" %>
    <div class="main-content">
        <div class="content" id = "content">
            <h2>Tin ngày hôm nay</h2>
            <%
    try {
        List<News> newsList = (List<News>) request.getAttribute("newsList");
        if (newsList != null && !newsList.isEmpty()) {
            for (News news : newsList) {
    %>
        <div class="news-item" onclick="loadContent('bangtinchitiet.jsp?id=<%=news.getId()%>')">
            <img src="<%= request.getContextPath() %>/images/<%= news.getImage() %>" alt="Ảnh">
            <div>
                <h3><%= news.getTitle() %></h3>
                <p><%= news.getContent() %></p>
                <span>Posted on: <%= news.getPostedDate() %> | Author: <%= news.getAuthor() %></span>
            </div>
        </div>
    <%
            }
        } else {
    %>
        <p>No news available at the moment.</p>
    <%
        }
    } catch (Exception e) {
        out.println("<p>Error loading news: " + e.getMessage() + "</p>");
    }
%>


        </div>

        <aside class="sidebar">
            <h3><a href="news?action=top5hot">5 Bản tin được xem nhiều</a></h3>
    <h3><a href="news?action=top5latest">5 Bản tin mới nhất</a></h3>
    <h3><a href="news?action=recent&userId=<%= Auth.getMaNV() %>">5 Bản tin đã xem</a></h3>
<form action="newsletterSubscription" method="post">
    <input type="email" name="email" placeholder="Nhập email của bạn" value="<%= Auth.getEmail() %>" required>
    
    <input type="hidden" id="actionType" name="actionType" value="subscribe">
	<br>
	<br>
    <button type="submit" onclick="setAction('subscribe')">Đăng ký</button>
    <button type="submit" onclick="setAction('unsubscribe')">Hủy đăng ký</button>
</form>
        </aside>
    </div>

    <%@include file = "/common/footer.jsp" %>
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
function setAction(action) {
    document.getElementById('actionType').value = action;
}
</script>
</html>

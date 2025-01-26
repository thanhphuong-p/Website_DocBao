<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.News" %>

<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    body {
        font-family: 'Helvetica Neue', sans-serif;
        background-color: #f5f5f5;
        color: #333;
        padding-top: 0px; 
    }
    h2 {
        color: #2c3e50;
        margin-bottom: 20px;
        text-align: center;
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
    header, footer {
        background-color: #2c3e50;
        color: white;
        padding: 15px 0;
        text-align: center;
        width: 100%;
        z-index: 1000;
    }
    header {
        top: 0;
    }
    footer {
        bottom: 0;
    }
    
    .container {
        padding-bottom: 80px; 
    }
    nav ul li a:hover {
        color: #f1c40f;
    }
</style>

<h2>Tin Tức Nổi Bật</h2>
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

<script>
    function loadContent(url) {
        window.location.href = url;
    }
</script>

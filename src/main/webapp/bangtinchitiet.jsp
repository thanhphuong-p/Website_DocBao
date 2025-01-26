<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.NewsDAO" %>
<%@ page import="Entity.News" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Comment" %>
<%@ page import="DAO.CommentDAO" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<%
    // Retrieve the ID from the query string
    String id = request.getParameter("id");

    // Initialize DAO to fetch the news based on ID
    NewsDAO newsDAO = new NewsDAO();
    News newsDetail = newsDAO.selectById(id);

    // Set newsDetail as a request attribute for EL access
    request.setAttribute("newsDetail", newsDetail);
    
    CommentDAO CommentDAO = new CommentDAO();		
    List<Comment> comments = CommentDAO.selectByCommentId(id);
    request.setAttribute("comments", comments);
%>

<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    body {
        font-family: 'Helvetica Neue', sans-serif;
        background-color: #f5f5f5;
    }
    .news-detail {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        margin-bottom: 30px;
    }
    .news-detail img {
        width: 100%;
        max-width: 600px;
        margin-bottom: 20px;
        border-radius: 8px;
    }
    .news-detail h2 {
        color: #2c3e50;
        margin-bottom: 15px;
    }
    .news-detail p {
        font-size: 16px;
        line-height: 1.6;
        margin-bottom: 20px;
    }
    .news-detail span {
        font-size: 14px;
        color: #7f8c8d;
    }.news-detail button {
            padding: 10px 20px;
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    .related-news {
        margin-top: 30px;
    }
    .related-news h3 {
        margin-bottom: 10px;
        color: #2980b9;
    }
    .related-news a {
        display: block;
        color: #3498db;
        margin-bottom: 8px;
        text-decoration: none;
    }
    .related-news a:hover {
        text-decoration: underline;
    }
    header, footer {
        background-color: #2c3e50;
        color: white;
        padding: 15px 0;
        text-align: center;
        position: relative; 
        width: 100%; 
        left: 0; 
        right: 0; 
    }
    nav {
        background-color: #ecf0f1;
        padding: 10px 0;
        margin-bottom: 20px; 
    }
    nav ul {
        list-style: none;
        display: flex;
        justify-content: center;
    }
    nav ul li {
        margin: 0 15px;
    }
    nav ul li a {
        text-decoration: none;
        color: #2980b9;
        font-weight: bold;
    }
    .container {
        padding-bottom: 80px;
    }
    h2 {
        color: #2c3e50;
        margin-bottom: 20px;
        text-align: center;
    }
    nav ul li a:hover {
        color: #f1c40f;
    }
    .comments-section {
    margin-top: 30px;
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.comments-section h3 {
    color: #2980b9;
    margin-bottom: 20px;
    font-size: 20px;
    border-bottom: 2px solid #ecf0f1;
    padding-bottom: 10px;
}

.comment {
    margin-bottom: 20px;
    padding-bottom: 10px;
    border-bottom: 1px solid #ecf0f1;
}

.comment:last-child {
    border-bottom: none; /* Bỏ border cuối cùng */
}

.comment p {
    margin: 5px 0;
    font-size: 16px;
    line-height: 1.5;
}

.comment strong {
    color: #2c3e50;
}

.comment span {
    font-size: 12px;
    color: #7f8c8d;
}

.comment-time {
    display: block;
    margin-top: 5px;
    font-size: 12px;
    color: #95a5a6;
}

/* Tùy chọn: Tạo các hộp màu khác nhau cho mỗi bình luận để tạo sự phân biệt */
.comment:nth-child(odd) {
    background-color: #f9f9f9;
    padding: 10px;
    border-radius: 5px;
}

.comment:nth-child(even) {
    background-color: #fdfdfd;
    padding: 10px;
    border-radius: 5px;
}
    
</style>


<div class="news-detail">
    <h2>${newsDetail.title}</h2>
    <img src="${pageContext.request.contextPath}/images/${newsDetail.image}" alt="Ảnh bản tin">
    <p>${newsDetail.content}</p>
    <span>Ngày đăng: ${newsDetail.postedDate} | Tác giả: ${newsDetail.author}</span>
    <br>
    <br>
    <button onclick="location.href='http://localhost:8080/asm/docgia'">Quay về trang chủ</button>
    
</div>

<div class="comments-section">
    <h3>Bình luận</h3>
    <c:forEach var="comment" items="${comments}">
        <div class="comment">
            <p><strong>${comment.id}:</strong> ${comment.content}</p>
            <span class="comment-time">Ngày: ${comment.commentDate}</span>
        </div>
    </c:forEach>
    
     <h4>Thêm bình luận:</h4>
    <form action="addComment" method="POST">
        <input type="hidden" name="newsId" value="${newsDetail.id}">
        <textarea name="content" required placeholder="Nhập bình luận..."></textarea>
        <button type="submit">Gửi bình luận</button>
    </form>
</div>
</div>




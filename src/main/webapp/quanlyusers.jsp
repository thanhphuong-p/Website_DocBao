<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.NhanVien" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Người Dùng</title>
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
        input[type="radio"], label[for="gender_male"], label[for="gender_female"],
label[for="role_admin"], label[for="role_user"] {
    display: inline-block;
    margin-right: 10px;
}
         </style>
</head>
<body>

    <%@ include file = "/common/header_admin.jsp" %>

    <h2>Quản lý Người Dùng</h2>
    <div class="card">

        <!-- FORM -->
        <form method="post" enctype="multipart/form-data">
            <label for="id">ID:</label>
            <input type="text" id="id" name="id" value="${nv != null ? nv.id : ''}" required>

            <label for="fullname">Họ tên:</label>
            <input type="text" id="fullname" name="fullname" value="${nv != null ? nv.fullname : ''}" required>

            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="${nv != null ? nv.email : ''}" required>

			<label for="password">Password:</label>
            <input type="text" id="password" name="password" value="${nv != null ? nv.password : ''}" required>
			

            <label for="mobile">Số điện thoại:</label>
            <input type="text" id="mobile" name="mobile" value="${nv != null ? nv.mobile : ''}" required>

            <label for="birthday">Ngày sinh:</label>
            <input type="text" id="birthday" name="birthday" value="${nv != null ? nv.birthday : ''}" required>

            <label>Giới tính:</label>
			<label for="gender_male">Nữ</label>
			<input type="radio" id="gender_male" name="gender" value="1" ${nv != null && nv.gender ? 'checked' : ''}>

			<label for="gender_female">Nam</label>
			<input type="radio" id="gender_female" name="gender" value="0" ${nv != null && !nv.gender ? 'checked' : ''}>
            

            <label>Vai trò:</label>
            <label for="role_admin">Người dùng</label>
            <input type="radio" id="role_admin" name="role" value="1" ${nv != null && nv.role ? 'checked' : ''}>
            <label for="role_user">Quản trị viên</label>
            <input type="radio" id="role_user" name="role" value="0" ${nv != null && !nv.role ? 'checked' : ''}>
            

			<br>


            <c:choose>
                <c:when test="${nv == null}">
                    <button formaction="${path}/asm/users/create">Tạo mới</button>
                </c:when>
                <c:otherwise>
                    <button formaction="${path}/asm/users/update">Cập nhật</button>
                    <button formaction="${path}/asm/users/delete" 
                            onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</button>
                </c:otherwise>
            </c:choose>
            <button formaction="${path}/asm/users/reset">Reset</button>
        </form>

        <!-- TABLE -->
        <table>
            <thead>
                <tr>
                    <th>Họ tên</th>
                    <th>Email</th>
                    <th>Số điện thoại</th>
                    <th>Ngày sinh</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.fullname}</td>
                        <td>${user.email}</td>
                        <td>${user.mobile}</td>
                        <td>${user.birthday}</td>
                        <td>
                            <a href="${path}/asm/users/edit/${user.getId()}">Sửa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file = "/common/footer_admin.jsp" %>

</body>
</html>

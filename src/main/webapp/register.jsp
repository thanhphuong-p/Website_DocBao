<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .register-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        input[type="text"], input[type="email"], input[type="password"], input[type="tel"], input[type="id"], input[type="birthday"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .login-link {
            display: block;
            text-align: center;
            margin-top: 10px;
        }
        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }
        .gender-role-container {
            margin: 10px 0;
        }
    </style>
</head>
<body>

<div class="register-container">
    <h2>Register</h2>
    <form action="register" method="post">
        <div class="error-message">${errorMessage != null ? errorMessage : ""}</div>
        <input type="text" name="fullname" placeholder="Full Name" required>
        <input type="id" name="id" placeholder="ID" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="birthday" name="birthday" placeholder="Birthday (dd/MM/yyyy)" required>
        <input type="tel" name="mobile" placeholder="Mobile" required>

        <!-- Gender Selection -->
        <div class="gender-role-container">
            <label>Gender:</label><br>
            <input type="radio" id="male" name="gender" value="male" required>
            <label for="male">Female</label>
            <input type="radio" id="female" name="gender" value="female" required>
            <label for="female">Male</label>
        </div>

        <!-- Role Selection -->
        <div class="gender-role-container">
            <label>Role:</label><br>
            <input type="radio" id="admin" name="role" value="admin" required>
            <label for="admin">Reader</label>
            <input type="radio" id="reader" name="role" value="reader" required>
            <label for="reader">Admin</label>
        </div>

        <input type="submit" value="Register">
        <a class="login-link" href="login.jsp">Already have an account? Login</a>
    </form>
</div>

</body>
</html>

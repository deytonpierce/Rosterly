<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- JSTL Core Tag Library for using core tags -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Spring Form Tag Library for handling form inputs and validation -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register & Login</title>
    <!-- Bootstrap CSS for styling -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <!-- Custom CSS for matcha theme -->
    <style>
        body {
            background: linear-gradient(to right, #d3e4cd, #99a98f); /* Matcha-inspired gradient */
            color: #4d6656; /* Earthy green for text */
        }
        .container {
            background-color: #f6f4f1; /* Light beige for a soft contrast */
            padding: 2rem;
            border-radius: 10px;
            margin-top: 2rem;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); /* Soft shadow */
            max-width: 600px;
        }
        .rosterly-logo {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-size: 36px;
            font-weight: bold;
            background: linear-gradient(90deg, #4CAF50, #2196F3);
            -webkit-background-clip: text;
            color: transparent;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .rosterly-logo:hover {
            color: #2196F3; /* Hover effect */
        h1, h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            font-family: 'Arial', sans-serif;
        }
        .form-group {
            margin-bottom: 1.5rem;
        }
        label {
            color: #4d6656; /* Matcha green for labels */
            font-weight: bold;
        }
        .form-control {
            border-radius: 5px;
            border: 1px solid #99a98f; /* Matcha green for input borders */
        }
        .form-control:focus {
            box-shadow: 0 0 5px rgba(153, 169, 143, 0.8); /* Green glow on focus */
            border-color: #7d9c6e;
        }
        .text-danger {
            color: #e06c75; /* Soft red for errors */
        }
        button.btn-primary {
            background-color: #99a98f; /* Matcha green for buttons */
            border: none;
            color: white;
            padding: 0.75rem 1.5rem;
            font-size: 1rem;
            border-radius: 5px;
        }
        button.btn-primary:hover {
            background-color: #7d9c6e; /* Darker matcha green on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="/teams" class="rosterly-logo">Rosterly</a>
	</div>
	<div  class="container">
        <h2>Register</h2>
        <!-- Form for user registration -->
        <form:form action="/register/user" method="post" modelAttribute="newUser">
            <div class="form-group">
                <!-- Username input field -->
                <form:label path="username">Username:</form:label>
                <form:input type="text" path="username" class="form-control" />
                <form:errors class="text-danger" path="username" />
            </div>
            <div class="form-group">
                <!-- Email input field -->
                <form:label path="email">Email:</form:label>
                <form:input type="text" path="email" class="form-control" />
                <form:errors class="text-danger" path="email" />
            </div>
            <div class="form-group">
                <!-- Password input field -->
                <form:label path="password">Password:</form:label>
                <form:input type="password" path="password" class="form-control" />
                <form:errors class="text-danger" path="password" />
            </div>
            <div class="form-group">
                <!-- Confirm Password input field -->
                <form:label path="confirm">Confirm Password:</form:label>
                <form:input type="password" path="confirm" class="form-control" />
                <form:errors class="text-danger" path="confirm" />
            </div>
            <!-- Submit button for registration -->
            <button type="submit" class="btn btn-primary">Register</button>
        </form:form>

        <h2 class="mt-5">Login</h2>
        <!-- Form for user login -->
        <form:form action="/login/user" method="post" modelAttribute="newLogin">
            <div class="form-group">
                <!-- Email input field -->
                <form:label path="email">Email:</form:label>
                <form:input type="text" path="email" class="form-control" />
                <form:errors class="text-danger" path="email" />
            </div>
            <div class="form-group">
                <!-- Password input field -->
                <form:label path="password">Password:</form:label>
                <form:input type="password" path="password" class="form-control" />
                <form:errors class="text-danger" path="password" />
            </div>
            <!-- Submit button for login -->
            <button type="submit" class="btn btn-primary">Login</button>
        </form:form>
    </div>
</body>
</html>
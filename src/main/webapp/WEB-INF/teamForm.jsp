<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- JSTL Core Tag Library for core functionalities in JSP -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Spring Form Tag Library for handling form inputs and validation -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add A Team</title>
    <!-- Bootstrap CSS for styling -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <style>
        body {
            background: linear-gradient(to right, #d3e4cd, #99a98f); /* Soft matcha-inspired gradient */
            color: #4d6656; /* Earthy green for text */
        }
        .container {
            background-color: #f6f4f1; /* Light beige for a soft contrast */
            padding: 2rem;
            border-radius: 10px;
            margin-top: 2rem;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); /* Soft shadow */
        }
        h1 {
            text-align: center;
            margin-bottom: 1.5rem;
            font-family: 'Arial', sans-serif;
        }
        .btn-primary, .btn-danger {
            margin: 0.5rem 0;
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
        }
        .btn-primary {
            background-color: #99a98f; /* Matcha green */
            color: white;
        }
        .btn-primary:hover {
            background-color: #7d9c6e; /* Darker shade for hover */
        }
        .btn-danger {
            background-color: #e06c75; /* Soft red */
            color: white;
        }
        .btn-danger:hover {
            background-color: #d15361; /* Darker red on hover */
        }
        .form-group {
            margin-bottom: 1.5rem; /* Spacing between form groups */
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
    </style>
</head>
<body>
    <div class="container">
    	<a href="/teams" class="rosterly-logo">Rosterly</a>
        <!-- Header for the page -->
        <h1>Add A Team!</h1>
        <!-- Form to add a new team -->
        <form:form action="/teams/new/process" method="post" modelAttribute="team">
            <div class="form-group">
                <!-- Label and input for team name -->
                <form:label path="name">Team Name:</form:label>
                <form:input type="text" path="name" class="form-control" />
                <form:errors class="text-danger" path="name" />
            </div>
            <div class="form-group">
                <!-- Label and input for skill level with constraints -->
                <form:label path="skillLevel">Skill Level (1-5):</form:label>
                <form:input type="number" path="skillLevel" class="form-control" min="1" max="5" />
                <form:errors class="text-danger" path="skillLevel" />
            </div>
            <div class="form-group">
                <!-- Label and input for game day -->
                <form:label path="gameDay">Game day:</form:label>
                <form:input type="text" path="gameDay" class="form-control" />
                <form:errors class="text-danger" path="gameDay" />
            </div>
            <!-- Buttons to submit the form or cancel the action -->
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Create</button>
                <a href="/teams" class="btn btn-danger">Cancel</a>
            </div>
        </form:form>
    </div>
</body>
</html>
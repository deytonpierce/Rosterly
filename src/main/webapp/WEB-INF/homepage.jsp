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
    <title>Team Dashboard</title>
    <!-- Bootstrap CSS for styling -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <!-- Custom CSS for matcha theme and Rosterly logo -->
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
        .table {
            background-color: white; /* Solid white for better readability */
        }
        thead {
            background-color: #99a98f; /* Matcha green for the header */
            color: white;
        }
        th, td {
            color: #4d6656; /* Darker matcha green */
        }
        td a {
            color: #4d6656; /* Link color matching the theme */
            text-decoration: none;
        }
        td a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Rosterly clickable logo -->
        <a href="/teams" class="rosterly-logo">Rosterly</a>
        
        <h1>Welcome, <c:out value="${user.username}!" /></h1>
        
        <div class="d-flex justify-content-between">
            <a href="/new/team" class="btn btn-primary">Add Team</a>
            <a href="/logout" class="btn btn-danger">Logout</a>
        </div>
        
        <!-- Table to display teams and their details -->
        <table class="table table-striped table-hover mt-4">
            <thead>
                <tr>
                    <th>Team Name</th>
                    <th>Skill Level (1-5)</th>
                    <th>Players</th>
                    <th>Game Day</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through the list of teams and display their details -->
                <c:forEach var="team" items="${teams}">
                    <tr>
                        <!-- Team name with a link to the team details page -->
                        <td><a href="/teams/${team.id}">${team.name}</a></td>
                        <!-- Skill level of the team -->
                        <td>${team.skillLevel}</td>
                        <td>
                            <!-- Display the number of players with a maximum of 9 -->
                            <c:out value="${playerCounts[team.id]}/9" />
                        </td>
                        <!-- Game day of the team -->
                        <td>${team.gameDay}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
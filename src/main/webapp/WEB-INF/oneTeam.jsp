<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- JSTL Core Tag Library for core functionalities in JSP -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Spring Form Tag Library for handling form inputs and validation -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Charset and viewport settings for responsiveness -->
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>One Team</title>
    <!-- Bootstrap CSS for styling -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS for matcha theme -->
    <style>
        body {
            background: linear-gradient(to right, #d3e4cd, #99a98f); /* Soft matcha gradient */
            color: #4d6656; /* Earthy green for text */
        }
        .container {
            background-color: #f6f4f1; /* Light beige background */
            padding: 2rem;
            border-radius: 10px;
            margin-top: 2rem;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); /* Soft shadow */
        }
        .card {
            background-color: white;
        }
        .btn-primary, .btn-secondary, .btn-danger {
            margin: 0.5rem 0;
            padding: 0.75rem 1.5rem;
            border-radius: 5px;
        }
        .btn-primary {
            background-color: #99a98f;
        }
        .btn-primary:hover {
            background-color: #7d9c6e;
        }
        .btn-danger {
            background-color: #e06c75;
        }
        .btn-danger:hover {
            background-color: #d15361;
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
    </style>
</head>
<body>
    <div class="container mt-4">
    	<a href="/teams" class="rosterly-logo">Rosterly</a>
        <!-- Header for team details -->
        <h1 class="text-center">Team Details</h1>
        <!-- Card to display team information -->
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${team.name}</h5>
                <h6 class="card-subtitle mb-2 text-muted">Added by ${team.user.username}</h6>
                <p class="card-text">
                    <strong>Skill Level:</strong> ${team.skillLevel}<br> 
                    <strong>Game Day:</strong> ${team.gameDay}<br>
                </p>
                <!-- Button to navigate back to the dashboard -->
                <a href="/teams" class="btn btn-primary">Dashboard</a>
                
                <!-- Conditional buttons for editing and deleting -->
                <c:if test="${userId == team.user.id}">
                    <a href="/teams/${team.id}/edit" class="btn btn-secondary">Edit</a>
                    <form action="/teams/${team.id}/delete" method="post" style="display: inline;">
                        <input type="hidden" name="_method" value="delete" />
                        <input type="submit" value="Delete" class="btn btn-danger" />
                    </form>
                </c:if>
            </div>
        </div>

        <!-- Table to display the list of players in the team -->
        <table class="table table-striped mt-4">
            <thead>
                <tr>
                    <th>Players</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="player" items="${team.players}">
                    <tr>
                        <td>${player.name}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Form to add a new player -->
        <c:if test="${userId == team.user.id}">
            <form:form action="/teams/${team.id}/addplayer" method="post" modelAttribute="player">
                <div class="form-group">
                    <form:label path="name">Player Name:</form:label>
                    <form:input type="text" path="name" class="form-control" />
                    <form:errors class="text-danger" path="name" />
                </div>
                <button type="submit" class="btn btn-primary">Add Player</button>
            </form:form>
        </c:if>
    </div>
</body>
</html>
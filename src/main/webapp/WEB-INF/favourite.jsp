<%@ page import="ua.ithillel.tomcat.model.vm.MealItemVm" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zasobav
  Date: 14.08.23
  Time: 08:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hillel Meal App :: Meal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

    <style>
        .flex-container {
            display: flex;
            flex-direction: row;
            flex-wrap: wrap;
            justify-content: center;
        }

        .flex-container .card {
            margin: 20px;
        }
    </style>
</head>
<body>

    <%
        List<MealItemVm> meals = (List<MealItemVm>) request.getAttribute("meals");
    %>

    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Hillel Meal App</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="meal">Meal</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="favourite">Favourites</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="flex-container">

                    <%
                        for (MealItemVm meal :
                                meals) {
                    %>
                    <div class="card" style="width: 18rem;">
                        <img src="<%= meal.getImageUrl() %>" class="card-img-top" alt="<%= meal.getName() %>">
                        <form class="card-body" action="meal" method="get">
                            <h5 class="card-title"><%= meal.getName() %></h5>
                            <input type="text" name="mealId" value="<%= meal.getId() %>" hidden>
                            <button type="submit" class="btn btn-primary">Recipe</button>
                        </form>
                    </div>
                    <%
                        }
                    %>

                </div>
            </div>
        </div>
    </div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>

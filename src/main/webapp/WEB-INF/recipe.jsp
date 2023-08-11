<%@ page import="ua.ithillel.tomcat.model.vm.MealItemVm" %>
<%@ page import="ua.ithillel.tomcat.model.vm.IngredientVm" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zasobav
  Date: 11.08.23
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    MealItemVm meal = (MealItemVm) request.getAttribute("meal");
%>

<html>
<head>
    <title>Hillel Meal App :: <%= meal.getName() %>></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

    <style>
        .meal-img {
            max-height: 30vh;
        }

        .list-container {
            padding: 20px;
            padding-left: 10px;
        }

        .meal-column {
            padding: 10px;
        }

        .long-text {
            padding: 10px;
            margin-top: 20px
        }
    </style>
</head>
<body>

    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="meal">Meal</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="favourite">Favourites</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-md-3 meal-column">
                <h1><%= meal.getName() %></h1>
                <img class="meal-img" src="<%= meal.getImageUrl() %>" alt="<%= meal.getName() %>">
            </div>
            <div class="col-md-9">
                <p class="long-text">
                    <%= meal.getRecipe() %>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 list-container">
                <div class="list-group ">
                    <%
                        List<IngredientVm> ingredientVms = meal.getIngredientVms();
                    %>

                    <%
                        for (IngredientVm ingredient:
                             ingredientVms) {
                    %>

                        <a href="#" class="list-group-item list-group-item-action">
                            <%= ingredient.getName() %> : <%= ingredient.getMeasure() %>
                        </a>

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

<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Week 4 Lab 1</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="/css/bootstrap.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1>Homepage</h1>
                    <p>Hello ${username}.</p>
                    <p><a href="/login?action=logout" class="btn btn-primary" role="button">Logout</a></p>
                </div>
            </div>

            
        </div>
    </body>
</html>
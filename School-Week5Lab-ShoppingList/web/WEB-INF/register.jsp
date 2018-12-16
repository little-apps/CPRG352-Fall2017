<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Week 5 Lab 1</title>
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
                    <h1>Shopping List</h1>
                    
                    <c:if test="${message != null}">
                        <div class="alert alert-danger" role="alert">${message}</div>
                    </c:if>

                    <c:if test="${info != null}">
                        <div class="alert alert-info" role="alert">${info}</div>
                    </c:if>
                    
                    <form class="form-inline" action="/shoppinglist" method="post">
                        <input type="hidden" name="action" value="register">
                        <input type="text" class="form-control" name="username" id="username">
                        <input type="submit" value="Register" class="btn btn-default">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

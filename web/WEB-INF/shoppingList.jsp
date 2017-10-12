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
                    
                    <p>Hello, <c:out value="${username}"/>. <a href="/shoppinglist?action=logout">Logout</a>.</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form class="form-inline" action="/shoppinglist" method="post">
                        <input type="hidden" name="action" value="add">
                        <input type="text" class="form-control" name="item" id="item">
                        <input type="submit" value="Add" class="btn btn-default">
                    </form>
                </div>
            </div>
                    
                    
            <div class="row">
                <div class="col-md-12">      
                    <form action="/shoppinglist" method="post">
                        <input type="hidden" name="action" value="delete">
                        <ul class="list-unstyled">
                            <c:forEach items="${items}" var="item">
                                <li>
                                    <div class="radio">
                                        <label>
                                          <input type="radio" name="item" value="<c:out value="${item}"/>">
                                          <c:out value="${item}"/>
                                        </label>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        
                        <button type="submit" class="btn btn-default">Remove</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

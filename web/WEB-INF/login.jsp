<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Week 6 Lab 1</title>
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
                    <h1>Login</h1>
                </div>
            </div>

            <c:if test="${message != null}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="alert alert-danger" role="alert">${message}</div>
                    </div>
                </div>
            </c:if>

            <c:if test="${info != null}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="alert alert-info" role="alert">${info}</div>
                    </div>
                </div>
            </c:if>

            <form class="form-horizontal" action='/login' method='post'>
                <div class="form-group">
                  <label for="username" class="col-sm-2 control-label">Username</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="username" id="username" value="${username}">
                  </div>
                </div>
                <div class="form-group">
                  <label for="password" class="col-sm-2 control-label">Password</label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="password" id="password">
                  </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="rememberMe" <c:if test="${username != null}">checked</c:if>> Remember me
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Login</button>
                  </div>
                </div>
            </form>
        </div>
    </body>
</html>
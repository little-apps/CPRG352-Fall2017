<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="custom" uri="/WEB-INF/tlds/login.tld"  %>
<%@taglib prefix="sait" uri="/WEB-INF/tlds/sait.tld"  %>
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
            
            <sait:debug>
                <div class="well">
                    Remote Host: ${pageContext.request.remoteHost}<br />
                    Session ID: ${pageContext.session.id}
                </div>
            </sait:debug>

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

            <custom:login />
        </div>
    </body>
</html>
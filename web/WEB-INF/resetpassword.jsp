<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Reset Password</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        
        <script src="http://code.jquery.com/jquery-3.2.1.js"></script>

        <!-- Latest compiled and minified JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1>Reset Password</h1>
                    
                    <c:if test="${errorMessage != null}">
                        <div class="alert alert-danger" role="alert"><c:out value="${errorMessage}"></c:out> <a href="/forgot-password">Go Back.</a></div>
                    </c:if>
                        
                    <c:if test="${resetToken}">
                        <form class="form-horizontal" action="/reset-password" method="post">
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">New Password</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" name="password" id="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="passwordConfirm" class="col-sm-2 control-label">New Password (Confirm)</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default">Change Password</button>
                                </div>
                            </div>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        
    </body>
</html>

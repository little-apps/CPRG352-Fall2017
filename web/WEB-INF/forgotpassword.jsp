<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Forgot Password</title>
        
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
                    <h1 class="text-center">Reset Password</h1>
                    
                    <c:if test="${errorMessage != null}">
                        <div class="alert alert-danger" role="alert"><c:out value="${errorMessage}"></c:out></div>
                    </c:if>
                        
                    <c:if test="${infoMessage != null}">
                        <div class="alert alert-info" role="alert"><c:out value="${infoMessage}"></c:out></div>
                    </c:if>
                        
                    <form class="form-horizontal" action="/forgot-password" method="post">
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">E-mail</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" name="email" id="email" placeholder="E-mail">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Reset Password</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

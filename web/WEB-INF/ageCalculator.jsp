<%-- 
    Document   : ageCalculator
    Created on : Sep 18, 2017, 12:36:38 PM
    Author     : 704199
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1>Age Next Birthday</h1>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12">
                    ${error}
                    
                    <form action="${pageContext.request.contextPath}/AgeCalculator" method="post" class="form-inline">
                        <div class="form-group">
                          <label for="currentAge">Enter your current age</label>
                          <input name="age" type="number" class="form-control" id="currentAge">
                        </div>
                        <button type="submit" class="btn btn-default">Age Next Birthday</button>
                    </form>
                        
                    <p>${message}</p>
                </div>
            </div>
        </div>
    </body>
</html>

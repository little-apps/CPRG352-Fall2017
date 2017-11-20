<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>NotesKeepr Reset Password</h1>
        
        <p>${errorMessage}</p>
        
        <c:if test="${resetToken != null}">
            <form action="/reset-password" method="post">
                New Password: <input type="password" name="password"><br>
                New Password (Confirm): <input type="password" name="passwordConfirm"><br>

                <input type="submit" value="Reset Password">
            </form>
        </c:if>
        
        
    </body>
</html>

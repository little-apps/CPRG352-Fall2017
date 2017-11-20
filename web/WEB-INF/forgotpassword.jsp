<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>NotesKeepr Reset Password</h1>
        
        <p>${errorMessage}</p>
        
        <form action="/forgot-password" method="post">
            username: <input type="text" name="username"><br>
            
            <input type="submit" value="Reset Password">
        </form>
    </body>
</html>

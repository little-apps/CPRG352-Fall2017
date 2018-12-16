<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage Users</title>
        
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
                    <h1>Manage Users</h1>
                    <h2>Users</h2>
                    
                    <c:if test="${errorMessage != null}">
                        <div class="alert alert-danger" role="alert"><c:out value="${errorMessage}"></c:out></div>
                    </c:if>
                        
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Role</th>
                                <th>Note</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.firstname}</td>
                                <td>${user.lastname}</td>
                                <td>${user.role.roleName}</td>
                                <td>
                                    <ul>
                                    <c:forEach var="note" items="${user.noteList}">
                                        <li>${note.title}</li>
                                    </c:forEach>
                                    </ul>
                                </td>
                                <td>
                                    <form class="form-inline" action="users" method="post" style="display: inline">
                                        <button type="submit" class="btn btn-link"><span class="glyphicon glyphicon-trash"></span></button>
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="selectedUsername" value="${user.username}">
                                    </form>
                                    <form class="form-inline" action="users" method="get" style="display: inline">
                                        <button type="submit" class="btn btn-link"><span class="glyphicon glyphicon-pencil"></span></button>
                                        <input type="hidden" name="action" value="view">
                                        <input type="hidden" name="selectedUsername" value="${user.username}">
                                    </form>
                                </td>
                            </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                    <c:if test="${selectedUser == null}">
                        <h3>Add User</h3>
                        <form class="form-horizontal" action="/users" method="post">
                            <input type="hidden" name="action" value="add">
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">Username</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="username" id="username">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstname" class="col-sm-2 control-label">First Name</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="firstname" id="firstname">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastname" class="col-sm-2 control-label">Last Name</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="lastname" id="lastname">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
                                    <input type="email" class="form-control" name="email" id="email">
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
                                            <input type="checkbox" name="active"> Active
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default">Save</button>
                                </div>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${selectedUser != null}">
                        <h3>Edit User</h3>
                        <form class="form-horizontal" action="/users" method="post">
                            <input type="hidden" name="action" value="edit">
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">Username</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="username" id="username" value="<c:out value="${selectedUser.username}" />" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstname" class="col-sm-2 control-label">First Name</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="firstname" id="firstname" value="<c:out value="${selectedUser.firstname}" />">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastname" class="col-sm-2 control-label">Last Name</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="lastname" id="lastname" value="<c:out value="${selectedUser.lastname}" />">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
                                    <input type="email" class="form-control" name="email" id="email" value="<c:out value="${selectedUser.email}" />">
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
                                            <input type="checkbox" name="active" ${selectedUser.active ? "checked" : ""}> Active
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default">Save</button>
                                </div>
                            </div>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        
    </body>
</html>

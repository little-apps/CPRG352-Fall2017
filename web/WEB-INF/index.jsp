<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />

<div class="row">
    <div class="col-md-12">
        <h1>Week 3 Lab 1</h1>
    </div>
</div>

<c:if test="${message != null}">
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-danger" role="alert">${message}</div>
        </div>
    </div>
</c:if>

<c:if test="${user == null}">
    <c:import url="/includes/login.jsp" />
</c:if>
    
<c:if test="${user != null}">
    <c:import url="/includes/user.jsp" />
</c:if>

<c:import url="/includes/footer.html" />
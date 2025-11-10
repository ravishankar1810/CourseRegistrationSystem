<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.student}">
    <c:redirect url="login.jsp" />
</c:if>

<%@ include file="header.jspf" %>

<title>Dashboard</title>

<div class="alert alert-primary" role="alert">
    <h4 class="alert-heading">Welcome, ${sessionScope.student.name}!</h4>
    <p>You have successfully logged in. You can manage your courses and profile from here.</p>
</div>

<h3 class="mt-4">Quick Actions</h3>
<div class="row mt-3">

    <div class="col-md-4 mb-3">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <h5 class="card-title">Course Catalog</h5>
                <p class="card-text">Browse, enroll, or drop courses.</p>
                <a href="catalog" class="btn btn-primary">Go to Catalog</a>
            </div>
        </div>
    </div>

    <div class="col-md-4 mb-3">
        <div class="card h-100 shadow-sm">
            <div class.card-body text-center">
            <h5 class="card-title">View Timetable</h5>
            <p class="card-text">See your current weekly schedule.</p>
            <a href="timetable" class="btn btn-primary">View Timetable</a>
        </div>
    </div>
</div>

    <div class="col-md-4 mb-3">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <h5 class="card-title">View Grades</h5>
                <p class="card-text">Check your academic performance.</p>
                <a href="grades" class="btn btn-primary">View Grades</a>
            </div>
        </div>
    </div>

</div>

<%@ include file="footer.jspf" %>
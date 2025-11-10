<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.student}">
    <c:redirect url="login.jsp" />
</c:if>

<%@ include file="header.jspf" %>

<title>My Timetable</title>

<h2>My Weekly Timetable</h2>
<p>Here is your current schedule of enrolled courses, ${sessionScope.student.name}.</p>

<div class="card shadow-sm mt-4">
    <div class="card-header">
        <h5>My Enrolled Courses</h5>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-hover align-middle">
                <thead class="table-light">
                <tr>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Instructor</th>
                    <th>Day(s)</th>
                    <th>Time</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="course" items="${timetableCourses}">
                    <tr>
                        <td><strong>${course.id}</strong></td>
                        <td>${course.name}</td>
                        <td>${course.instructor}</td>
                        <td>${course.dayOfWeek}</td>
                        <td>${course.time}</td>
                    </tr>
                </c:forEach>

                <c:if test="${empty timetableCourses}">
                    <tr>
                        <td colspan="5" class="text-center text-muted">You are not enrolled in any courses.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="footer.jspf" %>
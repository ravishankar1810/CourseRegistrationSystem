<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 11-11-2025
  Time: 02:36 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.student}">
    <c:redirect url="login.jsp" />
</c:if>

<%@ include file="header.jspf" %>

<title>My Grades</title>

<h2>My Academic History</h2>
<p>Here is a record of your past courses and grades, ${sessionScope.student.name}.</p>

<div class="card shadow-sm mt-4">
    <div class="card-header">
        <h5>My Grades</h5>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-hover align-middle">
                <thead class="table-light">
                <tr>
                    <th>Semester</th>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Final Grade</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="grade" items="${gradeList}">
                    <tr>
                        <td>${grade.semester}</td>
                        <td><strong>${grade.courseId}</strong></td>
                        <td>${grade.courseName}</td>
                        <td><h4><span class="badge bg-primary">${grade.letterGrade}</span></h4></td>
                    </tr>
                </c:forEach>

                <c:if test="${empty gradeList}">
                    <tr>
                        <td colspan="4" class="text-center text-muted">No grades on record.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="footer.jspf" %>
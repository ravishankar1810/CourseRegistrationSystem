<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.student}">
    <c:redirect url="login.jsp" />
</c:if>

<%@ include file="header.jspf" %>

<title>My Profile</title>

<h2>My Profile</h2>
<p>View your personal information and manage your password.</p>

<c:if test="${not empty message}">
    <div class="alert ${messageType == 'success' ? 'alert-success' : 'alert-danger'}
         alert-dismissible fade show mt-3" role="alert">
            ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>


<div class="row mt-4">
    <div class="col-md-6">
        <div class="card shadow-sm">
            <div class="card-header">
                <h5>My Information</h5>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <strong>Full Name:</strong> ${sessionScope.student.name}
                    </li>
                    <li class="list-group-item">
                        <strong>Username:</strong> ${sessionScope.student.username}
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="col-md-6">
        <div class="card shadow-sm">
            <div class="card-header">
                <h5>Change Password</h5>
            </div>
            <div class="card-body">
                <form action="profile" method="post">
                    <div class="mb-3">
                        <label for="currentPassword" class="form-label">Current Password</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                    </div>
                    <div class="mb-3">
                        <label for="newPassword" class="form-label">New Password</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">Confirm New Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Password</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jspf" %>
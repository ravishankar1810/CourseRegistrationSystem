<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jspf" %>

<title>Student Login</title>

<div class="row">
    <div class="col-lg-5 col-md-8 mx-auto">

        <div class="card shadow border-0">
            <div class="card-header text-center">
                <h3>Student Portal Login</h3>
            </div>
            <div class="card-body p-4">

                <p class="text-center text-muted">Welcome! Please sign in to continue.</p>

                <form action="login" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>

                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger" role="alert">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary btn-lg">Login</button>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>

<%@ include file="footer.jspf" %>
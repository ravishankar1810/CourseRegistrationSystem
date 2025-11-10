<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.student}">
    <c:redirect url="login.jsp" />
</c:if>

<%@ include file="header.jspf" %>

<title>Course Catalog</title>

<div class="d-flex justify-content-between align-items-center">
    <h2>Course Catalog</h2>
    <input type="text" id="courseSearch" class="form-control w-25"
           placeholder="Type to search courses...">
</div>


<c:if test="${not empty message}">
    <div class="alert ${messageType == 'success' ? 'alert-success' : 'alert-danger'}
         alert-dismissible fade show mt-3" role="alert">
            ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>


<div class="card shadow-sm mt-3">
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-hover align-middle">
                <thead class="table-light">
                <tr>
                    <th>Course ID</th>
                    <th>Name</th>
                    <th>Instructor</th>
                    <th>Seats Available</th>
                    <th class="text-center">Actions</th>
                </tr>
                </thead>
                <tbody id="courseTableBody">
                <c:forEach var="course" items="${courseList}">
                <tr>
                    <td><strong>${course.id}</strong></td>
                    <td>${course.name}</td>
                    <td>${course.instructor}</td>
                    <td>${course.remainingSeats} / ${course.totalSeats}</td>
                    <td>
                        <div classd-flex justify-content-center gap-2">
                        <form action="enroll" method="post" style="display:inline-block;">
                            <input type="hidden" name="courseId" value="${course.id}">
                            <c:choose>
                                <c:when test="${course.remainingSeats > 0}">
                                    <button type="submit" class="btn btn-success btn-sm">Enroll</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-secondary btn-sm" disabled>Full</button>
                                </c:otherwise>
                            </c:choose>
                        </form>

                        <form action="drop" method="post" style="display:inline-block;">
                            <input type="hidden" name="courseId" value="${course.id}">
                            <button type="submit" class="btn btn-danger btn-sm">Drop</button>
                        </form>
        </div>
        </td>
        </tr>
        </c:forEach>
        </tbody>
        </table>
    </div>
</div>
</div>

<%@ include file="footer.jspf" %>

<script>
    // Wait for the page to load
    document.addEventListener("DOMContentLoaded", function() {
        const searchInput = document.getElementById("courseSearch");
        const tableBody = document.getElementById("courseTableBody");
        const rows = tableBody.getElementsByTagName("tr");

        // Add an event listener to the search bar
        searchInput.addEventListener("keyup", function() {
            const filter = searchInput.value.toLowerCase();

            // Loop through all table rows
            for (let i = 0; i < rows.length; i++) {
                const cells = rows[i].getElementsByTagName("td");
                let text = cells[1].innerText + cells[2].innerText; // Search by Name or Instructor

                if (text.toLowerCase().indexOf(filter) > -1) {
                    rows[i].style.display = ""; // Show row
                } else {
                    rows[i].style.display = "none"; // Hide row
                }
            }
        });
    });
</script>
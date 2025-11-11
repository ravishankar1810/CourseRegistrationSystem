package com.example.servlet;

import com.example.dao.CourseDAO;
import com.example.dao.StudentDAO;
import com.example.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

// ... (imports) ...

@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {
    private CourseDAO courseDAO = new CourseDAO();
    private StudentDAO studentDAO = new StudentDAO(); // Keep this

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String courseId = request.getParameter("courseId");

        // --- THIS IS THE NEW LOGIC ---
        // 1. First, try to decrease the seat
        boolean seatDecreased = courseDAO.decreaseSeats(courseId);

        if (seatDecreased) {
            // 2. If seat was decreased, add to student's enrollments
            boolean enrolled = studentDAO.addEnrollment(student.getUsername(), courseId);

            if (enrolled) {
                // 3. Refresh the student object in the session
                session.setAttribute("student", studentDAO.getStudentByUsername(student.getUsername()));
                request.setAttribute("message", "Success! You have enrolled in " + courseId);
                request.setAttribute("messageType", "success");
            } else {
                // This could happen if they are already enrolled
                // Rollback: Give the seat back
                courseDAO.increaseSeats(courseId);
                request.setAttribute("message", "Enrollment Failed: You are already enrolled in this course.");
                request.setAttribute("messageType", "error");
            }
        } else {
            // This means the course is full
            request.setAttribute("message", "Enrollment Failed: Course is full.");
            request.setAttribute("messageType", "error");
        }
        // --- END OF NEW LOGIC ---

        request.getRequestDispatcher("/catalog").forward(request, response);
    }
}
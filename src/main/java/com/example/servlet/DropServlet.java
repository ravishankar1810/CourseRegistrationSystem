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

@WebServlet("/drop")
public class DropServlet extends HttpServlet {
    private final CourseDAO courseDAO = new CourseDAO();
    private final StudentDAO studentDAO = new StudentDAO(); // Keep this

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
        // 1. First, remove the enrollment record
        boolean enrollmentRemoved = studentDAO.removeEnrollment(student.getUsername(), courseId);

        if (enrollmentRemoved) {
            // 2. If removed, give the seat back
            courseDAO.increaseSeats(courseId);

            // 3. Refresh the student object in the session
            session.setAttribute("student", studentDAO.getStudentByUsername(student.getUsername()));
            request.setAttribute("message", "Dropped " + courseId + ". Seat freed!");
            request.setAttribute("messageType", "success");
        } else {
            // This means they were never enrolled
            request.setAttribute("message", "Could not drop " + courseId + " (You are not enrolled).");
            request.setAttribute("messageType", "error");
        }
        // --- END OF NEW LOGIC ---

        request.getRequestDispatcher("/catalog").forward(request, response);
    }
}
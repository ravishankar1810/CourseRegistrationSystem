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

@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {
    private CourseDAO courseDAO = new CourseDAO();
    private StudentDAO studentDAO = new StudentDAO();

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
        boolean success = courseDAO.decreaseSeats(courseId);

        if (success) {
            // Now, add the course to the student's list and save
            List<Student> allStudents = studentDAO.getAllStudents();
            for (Student s : allStudents) {
                if (s.getUsername().equals(student.getUsername())) {
                    if (!s.getEnrolledCourseIds().contains(courseId)) { // Avoid duplicates
                        s.getEnrolledCourseIds().add(courseId);
                        studentDAO.saveStudents(allStudents);
                        session.setAttribute("student", s); // Refresh session
                    }
                    break;
                }
            }

            request.setAttribute("message", "Success! You have enrolled in " + courseId);
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "Enrollment Failed: Course is full.");
            request.setAttribute("messageType", "error");
        }

        request.getRequestDispatcher("/catalog").forward(request, response);
    }
}
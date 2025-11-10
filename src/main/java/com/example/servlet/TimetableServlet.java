package com.example.servlet;

import com.example.dao.CourseDAO;
import com.example.model.Course;
import com.example.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet("/timetable")
public class TimetableServlet extends HttpServlet {
    private CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Security Check
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. Get student's enrolled course IDs
        List<String> enrolledIds = student.getEnrolledCourseIds();

        // 3. Get ALL courses from the DAO
        List<Course> allCourses = courseDAO.getAllCourses();

        // 4. Filter all courses to get only the ones the student is in
        List<Course> timetableCourses = new ArrayList<>();
        if (enrolledIds != null && !enrolledIds.isEmpty()) {
            timetableCourses = allCourses.stream()
                    .filter(course -> enrolledIds.contains(course.getId()))
                    .collect(Collectors.toList());
        }

        // 5. Pass data to JSP
        request.setAttribute("timetableCourses", timetableCourses);

        // 6. Forward to View
        request.getRequestDispatcher("timetable.jsp").forward(request, response);
    }
}
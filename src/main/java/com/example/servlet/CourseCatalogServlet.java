package com.example.servlet;

import com.example.dao.CourseDAO;
import com.example.model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/catalog")
public class CourseCatalogServlet extends HttpServlet {
    private CourseDAO courseDAO = new CourseDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get data from DAO
        List<Course> courses = courseDAO.getAllCourses();

        // 2. Pass data to JSP
        request.setAttribute("courseList", courses);

        // 3. Forward to View
        request.getRequestDispatcher("catalog.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // When EnrollServlet or DropServlet forwards a POST request,
        // just run the doGet logic to re-display the page.
        doGet(request, response);
    }
}
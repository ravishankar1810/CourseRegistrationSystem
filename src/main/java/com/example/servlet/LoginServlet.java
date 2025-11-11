package com.example.servlet;

import com.example.dao.StudentDAO;
import com.example.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// THIS IS THE CORRECT ANNOTATION
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        // This method now calls your MySQL database
        Student student = studentDAO.validateStudent(user, pass);

        if (student != null) {
            // Login successful: Create a session
            HttpSession session = request.getSession();
            session.setAttribute("student", student);
            response.sendRedirect("dashboard.jsp"); // Send to dashboard
        } else {
            // Login failed: Send back to login page with error
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
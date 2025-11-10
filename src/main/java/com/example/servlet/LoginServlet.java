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

// This annotation maps the URL "/login" to this Servlet
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        Student student = studentDAO.validateStudent(user, pass);

        if (student != null) {
            // Login successful: Create a session
            HttpSession session = request.getSession();
            session.setAttribute("student", student);
            response.sendRedirect("dashboard.jsp");
        } else {
            // Login failed: Send back to login page with error
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
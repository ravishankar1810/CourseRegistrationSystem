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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();

    // This method just SHOWS the profile page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Security check
        if (request.getSession(false) == null || request.getSession(false).getAttribute("student") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    // This method HANDLES the password change form
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // 1. Check if the current password is correct
        if (!student.getPassword().equals(currentPassword)) {
            request.setAttribute("message", "Incorrect current password.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            return;
        }

        // 2. Check if the new passwords match
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("message", "New passwords do not match.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            return;
        }

        // 3. Update the password in the database
        boolean success = studentDAO.updatePassword(student.getUsername(), newPassword);

        if (success) {
            // 4. Refresh the student object in the session with the new password
            student.setPassword(newPassword);
            session.setAttribute("student", student);
            request.setAttribute("message", "Password updated successfully!");
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "An error occurred. Password was not updated.");
            request.setAttribute("messageType", "error");
        }

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
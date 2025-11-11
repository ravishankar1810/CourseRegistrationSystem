package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// This servlet handles all requests to the "/logout" URL
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get the current session, but don't create a new one if it doesn't exist
        HttpSession session = request.getSession(false);

        if (session != null) {
            // 2. Invalidate the session (this logs the user out and deletes all session data)
            session.invalidate();
        }

        // 3. Redirect the user back to the login page
        response.sendRedirect("login.jsp");
    }
}
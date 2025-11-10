package com.example.servlet;

import com.example.dao.GradeDAO;
import com.example.model.Grade;
import com.example.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/grades")
public class GradesServlet extends HttpServlet {
    private GradeDAO gradeDAO = new GradeDAO();

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

        // 2. Get data from DAO
        List<Grade> grades = gradeDAO.getGradesByStudent(student.getUsername());

        // 3. Pass data to JSP
        request.setAttribute("gradeList", grades);

        // 4. Forward to View
        request.getRequestDispatcher("grades.jsp").forward(request, response);
    }
}
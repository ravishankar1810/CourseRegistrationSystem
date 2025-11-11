package com.example.dao;

import com.example.model.Grade;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {

    public List<Grade> getGradesByStudent(String username) {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT * FROM grades WHERE studentUsername = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    grades.add(mapGrade(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    private Grade mapGrade(ResultSet rs) throws SQLException {
        Grade g = new Grade();
        g.setStudentUsername(rs.getString("studentUsername"));
        g.setCourseId(rs.getString("courseId"));
        g.setCourseName(rs.getString("courseName"));
        g.setLetterGrade(rs.getString("letterGrade"));
        g.setSemester(rs.getString("semester"));
        return g;
    }
}
package com.example.dao;

import com.example.model.Student;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Validates a student by checking the database
    public Student validateStudent(String username, String password) {
        String sql = "SELECT * FROM students WHERE username = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapStudent(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Not found
    }

    // Gets a single student by username (needed for session refresh)
    public Student getStudentByUsername(String username) {
        String sql = "SELECT * FROM students WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapStudent(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Adds an enrollment record
    public boolean addEnrollment(String username, String courseId) {
        String sql = "INSERT INTO enrollments (studentUsername, courseId) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, courseId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // e.printStackTrace(); // Can be noisy (e.g., duplicate key)
            return false;
        }
    }

    // Removes an enrollment record
    public boolean removeEnrollment(String username, String courseId) {
        String sql = "DELETE FROM enrollments WHERE studentUsername = ? AND courseId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, courseId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Gets all enrolled course IDs for a student
    public List<String> getEnrolledCourseIds(String username) {
        List<String> courseIds = new ArrayList<>();
        String sql = "SELECT courseId FROM enrollments WHERE studentUsername = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    courseIds.add(rs.getString("courseId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseIds;
    }

    // Helper to build a Student object from a ResultSet
    private Student mapStudent(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setUsername(rs.getString("username"));
        s.setPassword(rs.getString("password"));
        s.setName(rs.getString("name"));
        // Get the list of enrolled courses and set it
        s.setEnrolledCourseIds(getEnrolledCourseIds(s.getUsername()));
        return s;
    }
    // ADD THIS NEW METHOD TO StudentDAO.java
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE students SET password = ? WHERE username = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, username);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
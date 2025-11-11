package com.example.dao;

import com.example.model.Course;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // Gets all courses from the DB
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                courses.add(mapCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Gets a single course by its ID
    public Course getCourseById(String courseId) {
        String sql = "SELECT * FROM courses WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCourse(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Decreases the seat count for a course
    public boolean decreaseSeats(String courseId) {
        String sql = "UPDATE courses SET remainingSeats = remainingSeats - 1 " +
                "WHERE id = ? AND remainingSeats > 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, courseId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Was the update successful?

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Increases the seat count for a course
    public boolean increaseSeats(String courseId) {
        String sql = "UPDATE courses SET remainingSeats = remainingSeats + 1 " +
                "WHERE id = ? AND remainingSeats < totalSeats";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, courseId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper to build a Course object from a ResultSet
    private Course mapCourse(ResultSet rs) throws SQLException {
        Course c = new Course();
        c.setId(rs.getString("id"));
        c.setName(rs.getString("name"));
        c.setInstructor(rs.getString("instructor"));
        c.setTotalSeats(rs.getInt("totalSeats"));
        c.setRemainingSeats(rs.getInt("remainingSeats"));
        c.setDayOfWeek(rs.getString("dayOfWeek"));
        c.setTime(rs.getString("time"));
        return c;
    }
}
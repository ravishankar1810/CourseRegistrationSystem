package com.example.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Grade {
    private String studentUsername;
    private String courseId;
    private String courseName;
    private String letterGrade;
    private String semester;

    // Empty constructor required for JAXB
    public Grade() {}

    // Getters and Setters
    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getLetterGrade() { return letterGrade; }
    public void setLetterGrade(String letterGrade) { this.letterGrade = letterGrade; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
}
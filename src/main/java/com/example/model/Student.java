package com.example.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.ArrayList;

@XmlRootElement
public class Student {
    private String username;
    private String password;
    private String name;

    private List<String> enrolledCourseIds = new ArrayList<>();

    // You MUST have an empty constructor for JAXB
    public Student() {}

    public Student(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    // --- Getters and Setters for enrollment list ---
    @XmlElementWrapper(name = "enrolledCourseIds")
    @XmlElement(name = "courseId")
    public List<String> getEnrolledCourseIds() {
        return enrolledCourseIds;
    }
    public void setEnrolledCourseIds(List<String> enrolledCourseIds) {
        this.enrolledCourseIds = enrolledCourseIds;
    }

    // --- Original Getters and Setters (This is what was missing) ---
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
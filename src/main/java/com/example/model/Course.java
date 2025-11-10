package com.example.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Course {
    private String id;
    private String name;
    private String instructor;
    private int totalSeats;
    private int remainingSeats;

    // --- ADD THESE TWO NEW FIELDS ---
    private String dayOfWeek;
    private String time;

    // Must have empty constructor
    public Course() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    public int getRemainingSeats() { return remainingSeats; }
    public void setRemainingSeats(int remainingSeats) { this.remainingSeats = remainingSeats; }

    // --- ADD GETTERS/SETTERS FOR NEW FIELDS ---
    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}
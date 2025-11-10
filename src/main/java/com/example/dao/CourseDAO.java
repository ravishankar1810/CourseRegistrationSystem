package com.example.dao;

import com.example.model.Course;
import com.example.model.CourseList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class CourseDAO {
    private static final String FILE_PATH = "D:/CourseData/courses.xml";

    // READ: Get all courses
    public List<Course> getAllCourses() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();

            JAXBContext context = JAXBContext.newInstance(CourseList.class);
            Unmarshaller un = context.createUnmarshaller();
            CourseList list = (CourseList) un.unmarshal(file);
            return list.getCourses();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // WRITE: Save entire list back to XML
    public void saveCourses(List<Course> courses) {
        try {
            CourseList list = new CourseList();
            list.setCourses(courses);

            JAXBContext context = JAXBContext.newInstance(CourseList.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(list, new File(FILE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // NEW SYNCHRONIZED METHOD ADDS HERE
    public synchronized boolean decreaseSeats(String courseId) {
        try {
            List<Course> courses = getAllCourses();
            boolean found = false;
            for (Course c : courses) {
                if (c.getId().equals(courseId)) {
                    if (c.getRemainingSeats() > 0) {
                        c.setRemainingSeats(c.getRemainingSeats() - 1);
                        found = true;
                    } else {
                        return false; // No seats left!
                    }
                    break; // Found the course, stop looping
                }
            }

            if (found) {
                saveCourses(courses); // Write updated list back to XML
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // NEW: Add this method to increase seats (Drop course)
    public synchronized boolean increaseSeats(String courseId) {
        try {
            List<Course> courses = getAllCourses();
            for (Course c : courses) {
                if (c.getId().equals(courseId)) {
                    // Only increase if it is not already at max capacity
                    if (c.getRemainingSeats() < c.getTotalSeats()) {
                        c.setRemainingSeats(c.getRemainingSeats() + 1);
                        saveCourses(courses);
                        return true;
                    } else {
                        return false; // Already empty, can't drop more
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
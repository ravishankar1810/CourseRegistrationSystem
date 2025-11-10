package com.example.dao;

import com.example.model.Student;
import com.example.model.StudentList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class StudentDAO {
    private static final String FILE_PATH = "D:/CourseData/students.xml";

    // READ: Get all students
    public List<Student> getAllStudents() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();

            JAXBContext context = JAXBContext.newInstance(StudentList.class);
            Unmarshaller un = context.createUnmarshaller();
            StudentList list = (StudentList) un.unmarshal(file);
            return list.getStudents() != null ? list.getStudents() : new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // WRITE: Save entire list back to XML
    public synchronized void saveStudents(List<Student> students) {
        try {
            StudentList list = new StudentList();
            list.setStudents(students);

            JAXBContext context = JAXBContext.newInstance(StudentList.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(list, new File(FILE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VALIDATE: (Now uses the getAllStudents method)
    public Student validateStudent(String username, String password) {
        List<Student> students = getAllStudents();
        for (Student s : students) {
            if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                return s;
            }
        }
        return null;
    }
}
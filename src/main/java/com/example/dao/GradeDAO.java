package com.example.dao;

import com.example.model.Grade;
import com.example.model.GradeList;
import com.example.model.Student;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GradeDAO {
    private static final String FILE_PATH = "D:/CourseData/grades.xml";

    public List<Grade> getGradesByStudent(String username) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();

            JAXBContext context = JAXBContext.newInstance(GradeList.class);
            Unmarshaller un = context.createUnmarshaller();
            GradeList list = (GradeList) un.unmarshal(file);

            // Use Java Streams to filter the list
            return list.getGrades().stream()
                    .filter(grade -> grade.getStudentUsername().equals(username))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
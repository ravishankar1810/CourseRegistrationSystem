CREATE DATABASE course_registration;
-- Use the database we just created
USE course_registration;

-- 1. Students Table
CREATE TABLE students (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL
);

-- 2. Courses Table
CREATE TABLE courses (
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    instructor VARCHAR(100),
    totalSeats INT NOT NULL,
    remainingSeats INT NOT NULL,
    dayOfWeek VARCHAR(50),
    time VARCHAR(50)
);

-- 3. Grades Table
CREATE TABLE grades (
    grade_id INT PRIMARY KEY AUTO_INCREMENT,
    studentUsername VARCHAR(50) NOT NULL,
    courseId VARCHAR(10) NOT NULL,
    courseName VARCHAR(100),
    letterGrade VARCHAR(2),
    semester VARCHAR(50),
    FOREIGN KEY (studentUsername) REFERENCES students(username)
);

-- 4. Enrollments Table (The "Link" between Students and Courses)
-- This is how you track the timetable!
CREATE TABLE enrollments (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    studentUsername VARCHAR(50) NOT NULL,
    courseId VARCHAR(10) NOT NULL,
    FOREIGN KEY (studentUsername) REFERENCES students(username),
    FOREIGN KEY (courseId) REFERENCES courses(id),
    UNIQUE (studentUsername, courseId) -- Prevent enrolling in the same course twice
);

-- --- Insert Sample Data ---

-- 1. Students (Same as your XML)
INSERT INTO students (username, password, name) VALUES
('Ravi', 'p1', 'Ravi Shankar'),
('r2', 'p2', 'Rishu');

-- 2. Courses (Same as your XML)
INSERT INTO courses (id, name, instructor, totalSeats, remainingSeats, dayOfWeek, time) VALUES
('CS101', 'Intro to Java', 'Dr. Smith', 10, 10, 'Mon/Wed/Fri', '9:00 AM - 9:50 AM'),
('MATH200', 'Calculus II', 'Prof. Davis', 5, 5, 'Tue/Thu', '1:00 PM - 2:15 PM'),
('ART150', 'Art History', 'Dr. Lee', 2, 2, 'Mon/Wed', '11:00 AM - 12:15 PM');

-- 3. Grades (Same as your XML)
INSERT INTO grades (studentUsername, courseId, courseName, letterGrade, semester) VALUES
('Ravi', 'HIST101', 'World History', 'A-', 'Fall 2024'),
('Ravi', 'PHYS150', 'Intro to Physics', 'B+', 'Fall 2024'),
('r2', 'CHEM110', 'General Chemistry', 'A', 'Spring 2025');
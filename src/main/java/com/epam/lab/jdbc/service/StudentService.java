package com.epam.lab.jdbc.service;

import com.epam.lab.jdbc.entity.Student;

import java.sql.Connection;
import java.util.List;

/**
 *
 */
public interface StudentService {
    public void addStudent(Student student);
    public void updateStudent(Student student, int gradebook);
    public void deleteStudent(int gradebook);
    Student getStudentByGradebook(int gradebook);
    List<Student> getAllStudent();
}

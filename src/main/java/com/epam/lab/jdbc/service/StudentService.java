package com.epam.lab.jdbc.service;

import com.epam.lab.jdbc.entity.Student;

import java.sql.Connection;
import java.util.List;

/**
 *
 */
public interface StudentService {
    public void addStudent(Student student);

    public void updateStudent();

    public void deleteStudent();

    Student getStudentByGradebook();

    List<Student> getAllStudent();
}

package com.epam.lab.jdbc.service;

import com.epam.lab.jdbc.entity.Student;

/**
 *
 */
public interface StudentService {
    public void addStudent(Student student);
    public void updateStudent(Student student, int gradebook);
    public void deleteStudent(int gradebook);
}

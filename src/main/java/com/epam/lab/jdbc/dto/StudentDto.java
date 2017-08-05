package com.epam.lab.jdbc.dto;

import com.epam.lab.jdbc.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StudentDto {
    public Student getStudentByGradebook(Connection connection, int gradebook) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * from student s WHERE s.gradebook_no = ?;");
        preparedStatement.setInt(1, gradebook);
        ResultSet resultSet = preparedStatement.executeQuery();
        Student student = new Student();
        while (resultSet.next()) {
            student = getStudentData(resultSet);
        }
        return student;
    }

    public List<Student> getAllStudent(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * from student s;");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            students.add(getStudentData(resultSet));
        }
        return students;
    }

    private Student getStudentData(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setGradebook_no(resultSet.getInt("gradebook_no"));
        student.setFirst_name(resultSet.getString("first_name"));
        student.setLast_name(resultSet.getString("last_name"));
        student.setGender(resultSet.getString("gender"));
        student.setDob(resultSet.getDate("dob").toString());
        student.setPhone_number(resultSet.getString("phone_number"));
        student.setDepartment_fk(resultSet.getString("department_fk"));
        return student;
    }
}

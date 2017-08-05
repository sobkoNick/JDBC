package com.epam.lab.jdbc.dao;

import com.epam.lab.jdbc.entity.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 */
public class StudentDao {
    public void addStudent(Connection connection, Student student) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  student(gradebook_no, first_name, " +
                "last_name, gender, dob, phone_number, department_fk) " +
                "VALUES (?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, student.getGradebook_no());
        preparedStatement.setString(2, student.getFirst_name());
        preparedStatement.setString(3, student.getLast_name());
        preparedStatement.setString(4, student.getGender());
        preparedStatement.setDate(5, Date.valueOf(student.getDob()));
        preparedStatement.setString(6, student.getPhone_number());
        preparedStatement.setString(7, student.getDepartment_fk());
        preparedStatement.execute();
    }

    public void updateStudent(Connection connection, int gradebook, Student student) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET first_name = ?, last_name = ?, " +
                "dob = ?, phone_number = ?, department_fk = ? WHERE gradebook_no = ?;");
        preparedStatement.setString(1, student.getFirst_name());
        preparedStatement.setString(2, student.getLast_name());
        preparedStatement.setDate(3, Date.valueOf(student.getDob()));
        preparedStatement.setString(4, student.getPhone_number());
        preparedStatement.setString(5, student.getDepartment_fk());
        preparedStatement.setInt(6, gradebook);
        preparedStatement.execute();
    }

    public void deleteStudent(Connection connection, int gradebook) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM student WHERE gradebook_no = ?");
        preparedStatement.setInt(1, gradebook);
        preparedStatement.execute();
    }
}

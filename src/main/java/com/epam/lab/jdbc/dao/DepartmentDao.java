package com.epam.lab.jdbc.dao;

import com.epam.lab.jdbc.dto.StudentDto;
import com.epam.lab.jdbc.entity.Department;
import com.epam.lab.jdbc.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class DepartmentDao {
    public void addDepartment(Connection connection, Department department) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO department(dep_uuid, course, speciality) " +
                "VALUES (?,?,?)");
        preparedStatement.setString(1, department.getDep_uuid());
        preparedStatement.setInt(2, department.getCourse());
        preparedStatement.setString(3, department.getSpeciality());
        preparedStatement.execute();
    }

    public void updateDepartment(Connection connection, String dep_uuid, Department department) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE department SET dep_uuid=?, " +
                "course=?, speciality=? WHERE dep_uuid=?");
        preparedStatement.setString(1, department.getDep_uuid());
        preparedStatement.setInt(2, department.getCourse());
        preparedStatement.setString(3, department.getSpeciality());
        preparedStatement.setString(4, dep_uuid);
        preparedStatement.execute();
    }

    public void deleteDepartment(Connection connection, String dep_uuid) throws SQLException {
        try {
            connection.setAutoCommit(false);
            StudentDto studentDto = new StudentDto();
            List<Student> students = studentDto.getAllStudent(connection);




        } catch (SQLException e) {
            connection.rollback();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }


        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM department WHERE dep_uuid=?;");
        preparedStatement.setString(1, dep_uuid);
        preparedStatement.execute();

    }

}

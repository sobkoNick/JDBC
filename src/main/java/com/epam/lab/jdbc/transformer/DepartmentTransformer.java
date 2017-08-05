package com.epam.lab.jdbc.transformer;

import com.epam.lab.jdbc.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DepartmentTransformer {
    public List<Department> getAllDepartments(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from department;");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Department> departments = new ArrayList<>();
        while (resultSet.next()) {
            Department department = new Department();
            department.setDep_uuid(resultSet.getString("dep_uuid"));
            department.setCourse(resultSet.getInt("course"));
            department.setSpeciality(resultSet.getString("speciality"));
            departments.add(department);
        }
        return departments;
    }

}

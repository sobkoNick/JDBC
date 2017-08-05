package com.epam.lab.jdbc.service;

import com.epam.lab.jdbc.entity.Department;
import com.epam.lab.jdbc.entity.Student;

import java.util.List;

/**
 *
 */
public interface DepartmentService {
    public void addDep(Department department);
    public void updateDep(String dep_uuid, Department department);
    public void deleteDep(String dep_uuid);
    List<Department> getAllDepartments();
}

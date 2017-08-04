package com.epam.lab.jdbc.service;

import com.epam.lab.jdbc.entity.Department;

/**
 *
 */
public interface DepartmentService {
    public void addDep(Department department);
    public void updateDep(String dep_uuid, Department department);
    public void deleteDep(String dep_uuid);
}

package com.epam.lab.jdbc.serviceImpl;

import com.epam.lab.jdbc.SettingUpDataBase;
import com.epam.lab.jdbc.dao.DepartmentDao;
import com.epam.lab.jdbc.entity.Department;
import com.epam.lab.jdbc.service.DepartmentService;
import com.epam.lab.jdbc.sqlConst.SQLConst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public void addDep(Department department) {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            DepartmentDao departmentDao = new DepartmentDao();
            departmentDao.addDepartment(connection, department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDep(String dep_uuid, Department department) {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            DepartmentDao departmentDao = new DepartmentDao();
            departmentDao.updateDepartment(connection, dep_uuid, department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDep(String dep_uuid) {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            DepartmentDao departmentDao = new DepartmentDao();
            departmentDao.deleteDepartment(connection, dep_uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

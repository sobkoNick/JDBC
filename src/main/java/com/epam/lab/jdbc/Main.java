package com.epam.lab.jdbc;


import com.epam.lab.jdbc.dao.DepartmentDao;
import com.epam.lab.jdbc.dao.StudentDao;
import com.epam.lab.jdbc.entity.Department;
import com.epam.lab.jdbc.entity.Student;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class);
    static final String URL = "jdbc:mysql://localhost/?useUnicode=true&characterEncoding=UTF8";
    static final String USER = "root";
    static final String PASSWORD = "123";

    //    static Connection connection;
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        LOG.info("main() started");
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);
//        setUpDB();
//        addDep();
//        Thread.sleep(3000);
//        updateDep();
//        Thread.sleep(3000);
//        deleteDep();

//        addStudent();
//        Thread.sleep(3000);
//        updateStudent();
//        Thread.sleep(3000);
//        deleteStudent(3112070);
        LOG.info("main() ended");
    }

    private static void addStudent() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            SettingUpDataBase.setDBStatement(connection, "use jdbc_univer;");
            Student student = new Student(3112070, "Mykola", "Sobko", "M",
                    "1994-05-23", "09374055665");
            StudentDao studentDao = new StudentDao();
            studentDao.addStudent(connection, student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addDep() {
        LOG.info("try to add department");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            SettingUpDataBase.setDBStatement(connection, "use jdbc_univer;");
            Department department = new Department("fem31", 3, "madics");
            DepartmentDao departmentDao = new DepartmentDao();
            departmentDao.addDepartment(connection, department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateDep() {
        LOG.info("try to update department");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            SettingUpDataBase.setDBStatement(connection, "use jdbc_univer;");
            Department department = new Department("феі-51", 5, "наукиі");
            DepartmentDao departmentDao = new DepartmentDao();
            departmentDao.updateDepartment(connection, "fem31", department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteDep() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            SettingUpDataBase.setDBStatement(connection, "use jdbc_univer;");
            DepartmentDao departmentDao = new DepartmentDao();
            departmentDao.deleteDepartment(connection, "феі-51");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void deleteStudent(int gradebook) { // maybe it should be done via transaction
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            SettingUpDataBase.setDBStatement(connection, "use jdbc_univer;");
            StudentDao studentDao = new StudentDao();
            studentDao.deleteStudent(connection, gradebook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateStudent() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            SettingUpDataBase.setDBStatement(connection, "use jdbc_univer;");
            Student student = new Student(3112070, "Mykola", "QWE", "M",
                    "1994-05-11", "00000000");
            StudentDao studentDao = new StudentDao();
            studentDao.updateStudent(connection, 3112070, student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setUpDB() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            LOG.info("set up db");
            SettingUpDataBase.setUp(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

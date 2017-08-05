package com.epam.lab.jdbc;

import com.epam.lab.jdbc.dto.DepartmentDto;
import com.epam.lab.jdbc.dto.StudentDto;
import com.epam.lab.jdbc.entity.Department;
import com.epam.lab.jdbc.entity.Student;
import com.epam.lab.jdbc.serviceImpl.DepartmentServiceImpl;
import com.epam.lab.jdbc.serviceImpl.StudentServiceImpl;
import com.epam.lab.jdbc.sqlConst.SQLConst;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class SettingUpDataBase {
    public static void setUp(Connection connection) throws SQLException { // 1 transaction
        try {
            connection.setAutoCommit(false);

            setDBStatement(connection, SQLConst.DROP_DATABASE_IFEXIST);
            setDBStatement(connection, SQLConst.CREATE_DATABASE);
            setDBStatement(connection, SQLConst.USE_DATABASE);
            setDBStatement(connection, SQLConst.CREATE_TABLE_STUDENT);

            setDBStatement(connection, SQLConst.CREATE_TABLE_DEPARTMENT);
            setDBStatement(connection, SQLConst.WIRE_TABLES);
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void useUniverDB(Connection connection) throws SQLException {
        setDBStatement(connection, SQLConst.USE_DATABASE);
    }

    public static void setDBStatement(Connection connection, String sql) throws SQLException {
        Statement setUpDBStatement = null;
        try {
            setUpDBStatement = connection.createStatement();
            setUpDBStatement.execute(sql);
        } finally {
            if (setUpDBStatement != null) {
                setUpDBStatement.close();
            }
        }
    }

    public static void addDataToDB(Logger LOG) throws SQLException {
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        departmentService.addDep(new Department("феі21", 2, "комп науки"));
        departmentService.addDep(new Department("феі31", 3, "комп науки"));
        departmentService.addDep(new Department("фем11", 1, "медичні науки"));

        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.addStudent(new Student(3112070, "Mykola", "Sobko", "M", "1994-05-23", "0937405000", "феі31"));
        studentService.addStudent(new Student(3112050, "Andrei", "Tkass", "M", "1995-05-22", "0503505030", "феі21"));
        studentService.addStudent(new Student(3112020, "Vassa", "Rojo", "M", "1997-08-28", "0687002034", "фем11"));

        showAllDep(LOG);
        showAllStudents(LOG);
    }

    public static void showAllStudents(Logger LOG) throws SQLException {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            LOG.info("All students:");
            StudentDto studentDto = new StudentDto();
            studentDto.getAllStudent(connection).forEach(LOG::info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showAllDep(Logger LOG) throws SQLException {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            DepartmentDto departmentDto = new DepartmentDto();
            LOG.info("All departments:");
            departmentDto.getAllDepartments(connection).forEach(LOG::info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

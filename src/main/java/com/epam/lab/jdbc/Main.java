package com.epam.lab.jdbc;


import com.epam.lab.jdbc.dto.DepartmentDto;
import com.epam.lab.jdbc.dto.StudentDto;
import com.epam.lab.jdbc.entity.Department;
import com.epam.lab.jdbc.entity.Student;
import com.epam.lab.jdbc.serviceImpl.DepartmentServiceImpl;
import com.epam.lab.jdbc.sqlConst.SQLConst;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        LOG.info("main() started");
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);
        setUpDB();
        SettingUpDataBase.addDataToDB(LOG);

//        StudentDto studentDto = new StudentDto();
//        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
//            SettingUpDataBase.useUniverDB(connection);
//            Student student = studentDto.getStudentByGradebook(connection, 3112070);
//            LOG.info(student);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        LOG.info("main() ended");
    }

    private static void setUpDB() {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            LOG.info("set up db");
            SettingUpDataBase.setUp(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

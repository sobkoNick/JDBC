package com.epam.lab.jdbc;


import com.epam.lab.jdbc.dto.StudentDto;
import com.epam.lab.jdbc.entity.Student;
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
//        addDep();
//        Thread.sleep(3000);
//        updateDep();
//        Thread.sleep(3000);
//        deleteDep();

//        StudentDto studentDto = new StudentDto();
//        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
//            SettingUpDataBase.useUniverDB(connection);
//            Student student = studentDto.getStudentByGradebook(connection, 3112070);
//            LOG.info(student);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        Thread.sleep(3000);
//        updateStudent();
//
//        Thread.sleep(3000);
//        deleteStudent(3112070);
        LOG.info("main() ended");
    }

//    private static void addStudent() {
//        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
//            SettingUpDataBase.setDBStatement(connection, "use jdbc_univer;");
//            Student student = new Student(3112070, "Mykola", "Sobko", "M",
//                    "1994-05-23", "09374055665");
//            StudentDao studentDao = new StudentDao();
//            studentDao.addStudent(connection, student);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    private static void setUpDB() {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            LOG.info("set up db");
            SettingUpDataBase.setUp(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

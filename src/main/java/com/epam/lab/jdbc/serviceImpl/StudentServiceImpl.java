package com.epam.lab.jdbc.serviceImpl;

import com.epam.lab.jdbc.controller.Main;
import com.epam.lab.jdbc.controller.SettingUpDataBase;
import com.epam.lab.jdbc.dao.StudentDao;
import com.epam.lab.jdbc.transformer.StudentTransformer;
import com.epam.lab.jdbc.entity.Student;
import com.epam.lab.jdbc.service.StudentService;
import com.epam.lab.jdbc.sqlConst.SQLConst;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class StudentServiceImpl implements StudentService {
    Logger logger = Logger.getLogger(StudentServiceImpl.class);

    @Override
    public void addStudent(Student student) {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            StudentDao studentDao = new StudentDao();
            studentDao.addStudent(connection, student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent() {
        if (getAllStudent().isEmpty()) {
            logger.info("Student table is empty");
        } else {
            try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
                SettingUpDataBase.useUniverDB(connection);
                logger.info("print student gradebook_no");
                int gradebookToUpdate = Main.scanner.nextInt();
                Student studentUpdate = Main.getUpdatedStudentDataFromConsole();
                StudentDao studentDao = new StudentDao();
                studentDao.updateStudent(connection, gradebookToUpdate, studentUpdate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteStudent() {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            StudentDao studentDao = new StudentDao();
            StudentTransformer studentTransformer = new StudentTransformer();
            if (getAllStudent().isEmpty()) {
                logger.info("Student table is empty");
            } else {
                logger.info("print student gradebook_no");
                studentDao.deleteStudent(connection, Main.scanner.nextInt());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentByGradebook() {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            if (getAllStudent().isEmpty()) {
                logger.info("Student table is empty");
            } else {
                logger.info("print student gradebook_no");
                StudentTransformer studentTransformer = new StudentTransformer();
                return studentTransformer.getStudentByGradebook(connection, Main.scanner.nextInt());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAllStudent() {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            StudentTransformer studentTransformer = new StudentTransformer();
            return studentTransformer.getAllStudent(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

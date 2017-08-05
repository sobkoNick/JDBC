package com.epam.lab.jdbc.serviceImpl;

import com.epam.lab.jdbc.SettingUpDataBase;
import com.epam.lab.jdbc.dao.StudentDao;
import com.epam.lab.jdbc.dto.StudentDto;
import com.epam.lab.jdbc.entity.Student;
import com.epam.lab.jdbc.service.StudentService;
import com.epam.lab.jdbc.sqlConst.SQLConst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class StudentServiceImpl implements StudentService {
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
    public void updateStudent(Student student, int gradebook) {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            StudentDao studentDao = new StudentDao();
            studentDao.updateStudent(connection, gradebook, student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int gradebook) { // maybe it should be done via transaction
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            StudentDao studentDao = new StudentDao();
            studentDao.deleteStudent(connection, gradebook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentByGradebook(int gradebook) {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            StudentDto studentDto = new StudentDto();
            return studentDto.getStudentByGradebook(connection, gradebook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAllStudent() {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            StudentDto studentDto = new StudentDto();
            return studentDto.getAllStudent(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

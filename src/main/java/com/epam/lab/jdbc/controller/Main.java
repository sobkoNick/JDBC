package com.epam.lab.jdbc.controller;


import com.epam.lab.jdbc.entity.Department;
import com.epam.lab.jdbc.entity.Student;
import com.epam.lab.jdbc.serviceImpl.DepartmentServiceImpl;
import com.epam.lab.jdbc.serviceImpl.StudentServiceImpl;
import com.epam.lab.jdbc.sqlConst.SQLConst;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 */
public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class);
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        LOG.info("main() started");
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);
        setUpDB();
        LOG.info("database is setted");
        SettingUpDataBase.addDataToDB(LOG);
        boolean ifProgramToWork = true;
        boolean nextAction = true;
        while (ifProgramToWork) {
            LOG.info("---choose table to work with (s-students, d-department). any other to exit---");
            String table = scanner.next();
            nextAction = true;
            if (table.equalsIgnoreCase("s")) {
                while (nextAction) {
                    nextAction = workWithStudent(nextAction);
                }
            } else if (table.equalsIgnoreCase("d")) {
                while (nextAction) {
                    nextAction = doDepartmentActionsChanges(nextAction);
                }
            } else {
                ifProgramToWork = false;
            }
        }
        LOG.info("main() ended");
    }

    private static boolean doDepartmentActionsChanges(boolean nextAction) {
        LOG.info("--- (c)Create, (u)Update,(d)Delete in base Department. (gA) Get all students. 0 to exit from this table---");
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        String action = scanner.next();
        switch (action) {
            case "c":
                LOG.info("Input String dep_uuid, int course, String speciality");
                Department department = new Department();
                department.setDep_uuid(scanner.next());
                department.setCourse(scanner.nextInt());
                department.setSpeciality(scanner.next());
                departmentService.addDep(department);
                break;
            case "u":
                departmentService.updateDep();
                break;
            case "d":
                departmentService.deleteDep();
                break;
            case "gA":
                departmentService.getAllDepartments().forEach(LOG::info);
                break;
            case "0":
                nextAction = false;
                break;
        }
        return nextAction;
    }

    private static boolean workWithStudent(boolean nextAction) throws SQLException {
        StudentServiceImpl studentService = new StudentServiceImpl();
        LOG.info("--- (c)Create, (u)Update, (d)Delete in base Student. (gA) Get all students. (g1) Get 1. 0 to exit from this table---");
        String action = scanner.next();
        switch (action) {
            case "c":
                SettingUpDataBase.showAllDep(LOG);
                Student student = getStudentDataFromConsole();
                studentService.addStudent(student);
                break;
            case "u":
                studentService.updateStudent();
                break;
            case "d":
                studentService.deleteStudent();
                break;
            case "gA":
                studentService.getAllStudent().forEach(LOG::info);
                break;
            case "g1":
                LOG.info(studentService.getStudentByGradebook());
                break;
            case "0":
                nextAction = false;
                break;
        }
        return nextAction;
    }

    private static Student getStudentDataFromConsole() {
        LOG.info("Enter gradebook_no, first_name, last_name, gender(M/W), date of birth (YYYY-MM-DD), phone_number, department_uuid");
        Student student = new Student();
        student.setGradebook_no(scanner.nextInt());
        student.setFirst_name(scanner.next());
        student.setLast_name(scanner.next());
        student.setGender(scanner.next());
        student.setDob(scanner.next());
        student.setPhone_number(scanner.next());
        student.setDepartment_fk(scanner.next());
        return student;
    }

    public static Student getUpdatedStudentDataFromConsole() {
        LOG.info("Enter first_name, last_name, date of birth (YYYY-MM-DD), phone_number, department_uuid");
        Student student = new Student();
        student.setFirst_name(scanner.next());
        student.setLast_name(scanner.next());
        student.setDob(scanner.next());
        student.setPhone_number(scanner.next());
        student.setDepartment_fk(scanner.next());
        return student;
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

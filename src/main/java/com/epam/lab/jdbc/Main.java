package com.epam.lab.jdbc;


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

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        LOG.info("main() started");
        Scanner scanner = new Scanner(System.in);
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
                StudentServiceImpl studentService = new StudentServiceImpl();
                while (nextAction) {
                    nextAction = workWithStudent(scanner, nextAction, studentService);
                }
            } else if (table.equalsIgnoreCase("d")) {
                while (nextAction) {
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
                            LOG.info("Input dep_uuid to update that department");
                            String depUUIDToUpdate = scanner.next();
                            LOG.info("Input course, speciality");
                            Department departmentToUpdate = new Department();
                            departmentToUpdate.setCourse(scanner.nextInt());
                            departmentToUpdate.setSpeciality(scanner.next());
                            departmentService.updateDep(depUUIDToUpdate, departmentToUpdate);
                            break;
                        case "d":
                            ;
                            break;
                        case "gA":
                            departmentService.getAllDepartments().forEach(LOG::info);
                            break;
                        case "0":
                            nextAction = false;
                            break;
                    }
                }
            } else {
                ifProgramToWork = false;
            }
        }
        LOG.info("main() ended");
    }

    private static boolean workWithStudent(Scanner scanner, boolean nextAction, StudentServiceImpl studentService) throws SQLException {
        LOG.info("--- (c)Create, (u)Update, (d)Delete in base Student. (gA) Get all students. (g1) Get 1. 0 to exit from this table---");
        String action = scanner.next();
        switch (action) {
            case "c":
                SettingUpDataBase.showAllDep(LOG);
                Student student = getStudentDataFromConsole(scanner);
                studentService.addStudent(student);
                break;
            case "u":
                LOG.info("print student gradebook_no");
                int gradebookToUpdate = scanner.nextInt();
                Student studentUpdate = getUpdatedStudentDataFromConsole(scanner);
                studentService.updateStudent(studentUpdate, gradebookToUpdate);
                break;
            case "d":
                LOG.info("print student gradebook_no");
                studentService.deleteStudent(scanner.nextInt());
                break;
            case "gA":
                studentService.getAllStudent().forEach(LOG::info);
                break;
            case "g1":
                LOG.info("print student gradebook_no");
                LOG.info(studentService.getStudentByGradebook(scanner.nextInt()));
                break;
            case "0":
                nextAction = false;
                break;
        }
        return nextAction;
    }

    private static Student getStudentDataFromConsole(Scanner scanner) {
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

    private static Student getUpdatedStudentDataFromConsole(Scanner scanner) {
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

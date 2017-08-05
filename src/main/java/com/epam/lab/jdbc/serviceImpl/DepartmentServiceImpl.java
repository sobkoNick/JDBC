package com.epam.lab.jdbc.serviceImpl;

import com.epam.lab.jdbc.controller.Main;
import com.epam.lab.jdbc.controller.SettingUpDataBase;
import com.epam.lab.jdbc.dao.DepartmentDao;
import com.epam.lab.jdbc.dao.StudentDao;
import com.epam.lab.jdbc.transformer.DepartmentTransformer;
import com.epam.lab.jdbc.transformer.StudentTransformer;
import com.epam.lab.jdbc.entity.Department;
import com.epam.lab.jdbc.entity.Student;
import com.epam.lab.jdbc.service.DepartmentService;
import com.epam.lab.jdbc.sqlConst.SQLConst;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOG = Logger.getLogger(DepartmentServiceImpl.class);

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
    public void updateDep() {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            if (getAllDepartments().isEmpty()) {
                LOG.info("Department table is empty");
            } else {
                updateDepartment(connection);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateDepartment(Connection connection) throws SQLException {
        LOG.info("Input dep_uuid to update that department");
        String depUUIDToUpdate = Main.scanner.next();
        LOG.info("Input course, speciality");
        Department departmentToUpdate = new Department();
        departmentToUpdate.setCourse(Main.scanner.nextInt());
        departmentToUpdate.setSpeciality(Main.scanner.next());

        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.updateDepartment(connection, depUUIDToUpdate, departmentToUpdate);
    }

    @Override
    public List<Department> getAllDepartments() {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            DepartmentTransformer departmentTransformer = new DepartmentTransformer();
            return departmentTransformer.getAllDepartments(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteDep() {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            SettingUpDataBase.useUniverDB(connection);
            DepartmentDao departmentDao = new DepartmentDao();
            Scanner scanner = new Scanner(System.in);
            // get all students for department
            StudentDao studentDao = new StudentDao();
            StudentTransformer studentTransformer = new StudentTransformer();
            DepartmentTransformer departmentTransformer = new DepartmentTransformer();
            List<Department> departments = departmentTransformer.getAllDepartments(connection);
            if (departments.size() == 0) {
                LOG.info("Department table is empty");
            } else if (departments.size() < 2) {
                deleteOneLastDepartment(connection, departmentDao, scanner, studentDao, studentTransformer);
            } else {
                deleteDepartmentIfManyStillExist(connection, departmentDao, scanner, studentDao, studentTransformer, departments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteDepartmentIfManyStillExist(Connection connection, DepartmentDao departmentDao, Scanner scanner, StudentDao studentDao, StudentTransformer studentTransformer, List<Department> departments) throws SQLException {
        LOG.info("input dep_uuid to delete");
        String dep_uuid = scanner.next();
        List<Student> students = studentTransformer.getAllStudentFromDepartment(connection, dep_uuid);
        String dep_uuidForStudentsFromDeletedDep = "";
        for (Department department : departments) {
            if (!department.getDep_uuid().equalsIgnoreCase(dep_uuid)) {
                dep_uuidForStudentsFromDeletedDep = department.getDep_uuid();
                break;
            }
        }
        for (Student student : students) {
            student.setDepartment_fk(dep_uuidForStudentsFromDeletedDep);
            studentDao.updateStudent(connection, student.getGradebook_no(), student);
        }
        departmentDao.deleteDepartment(connection, dep_uuid);
        LOG.info("Department deleted");
    }

    private void deleteOneLastDepartment(Connection connection, DepartmentDao departmentDao, Scanner scanner, StudentDao studentDao, StudentTransformer studentTransformer) throws SQLException {
        LOG.info("One or none departments exist. If dep exist, it will be deleted and students will be without department");
        LOG.info("input dep_uuid to delete");
        String dep_uuid = scanner.next();
        List<Student> students = studentTransformer.getAllStudentFromDepartment(connection, dep_uuid);
        for (Student student : students) {
            student.setDepartment_fk(null);
            studentDao.updateStudent(connection, student.getGradebook_no(), student);
        }
        departmentDao.deleteDepartment(connection, dep_uuid);
        LOG.info("Department deleted");
    }


}

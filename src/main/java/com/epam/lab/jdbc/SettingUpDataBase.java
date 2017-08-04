package com.epam.lab.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class SettingUpDataBase {
    public static void setUp(Connection connection) throws SQLException { // 1 transaction
        try {
            connection.setAutoCommit(false);

            setDBStatement(connection, "DROP DATABASE IF EXISTS jdbc_univer;");
            setDBStatement(connection, "create database jdbc_univer default Character set utf8;");
            setDBStatement(connection, "use jdbc_univer;");
            setDBStatement(connection, "create table student(\n" +
                    "\tgradebook_no int primary key,\n" +
                    "    first_name varchar(40) not null,\n" +
                    "    last_name varchar(40) not null,\n" +
                    "    gender varchar(1) not null,\n" +
                    "    dob date not null,\n" +
                    "\tphone_number varchar(20),\n" +
                    "    department_fk varchar(20)\n" +
                    ") default Character set utf8;\n");

            setDBStatement(connection,"create table department(\n" +
                    "\tdep_uuid varchar(20) primary key,\n" +
                    "    course tinyint,\n" +
                    "    speciality VARCHAR(40)\n" +
                    ") default Character set utf8;");
            setDBStatement(connection, "alter table student add constraint\n" +
                    "\tforeign key(department_fk)\n" +
                    "    references department(dep_uuid);");
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
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
}

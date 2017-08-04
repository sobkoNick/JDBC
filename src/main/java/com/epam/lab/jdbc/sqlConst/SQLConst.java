package com.epam.lab.jdbc.sqlConst;

/**
 *
 */
public class SQLConst {
    public static final String URL = "jdbc:mysql://localhost/?useUnicode=true&characterEncoding=UTF8";
    public static final String USER = "root";
    public static final String PASSWORD = "123";
    public static final String DROP_DATABASE_IFEXIST = "DROP DATABASE IF EXISTS jdbc_univer;";
    public static final String CREATE_DATABASE = "create database jdbc_univer default Character set utf8;";
    public static final String USE_DATABASE = "use jdbc_univer;";
    public static final String CREATE_TABLE_STUDENT = "create table student(\n" +
            "\tgradebook_no int primary key,\n" +
            "    first_name varchar(40) not null,\n" +
            "    last_name varchar(40) not null,\n" +
            "    gender varchar(1) not null,\n" +
            "    dob date not null,\n" +
            "\tphone_number varchar(20),\n" +
            "    department_fk varchar(20)\n" +
            ") default Character set utf8;\n";

    public static final String CREATE_TABLE_DEPARTMENT = "create table department(\n" +
            "\tdep_uuid varchar(20) primary key,\n" +
            "    course tinyint,\n" +
            "    speciality VARCHAR(40)\n" +
            ") default Character set utf8;";

    public static final String WIRE_TABLES = "alter table student add constraint\n" +
            "\tforeign key(department_fk)\n" +
            "    references department(dep_uuid);";

}

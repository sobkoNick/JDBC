package com.epam.lab.jdbc.entity;

import java.util.Date;

/**
 *
 */
public class Student {
    Integer gradebook_no;
    String first_name;
    String last_name;
    String gender;
    String dob;
    String phone_number;
    String department_fk;

    public Student() {
    }

    public Student(Integer gradebook_no, String first_name, String last_name, String gender, String dob, String phone_number, String department_fk) {
        this.gradebook_no = gradebook_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.dob = dob;
        this.phone_number = phone_number;
        this.department_fk = department_fk;
    }

    public Student(Integer gradebook_no, String first_name, String last_name, String gender, String dob, String phone_number) {
        this.gradebook_no = gradebook_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.dob = dob;
        this.phone_number = phone_number;
    }

    public Integer getGradebook_no() {
        return gradebook_no;
    }

    public void setGradebook_no(Integer gradebook_no) {
        this.gradebook_no = gradebook_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDepartment_fk() {
        return department_fk;
    }

    public void setDepartment_fk(String department_fk) {
        this.department_fk = department_fk;
    }

    @Override
    public String toString() {
        return "Student{" +
                "gradebook_no=" + gradebook_no +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", department_fk='" + department_fk + '\'' +
                '}';
    }
}

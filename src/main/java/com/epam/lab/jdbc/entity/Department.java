package com.epam.lab.jdbc.entity;

/**
 *
 */
public class Department {
    String dep_uuid;
    Integer course;
    String speciality;

    public Department() {
    }

    public Department(String dep_uuid, Integer course, String speciality) {
        this.dep_uuid = dep_uuid;
        this.course = course;
        this.speciality = speciality;
    }

    public String getDep_uuid() {
        return dep_uuid;
    }

    public void setDep_uuid(String dep_uuid) {
        this.dep_uuid = dep_uuid;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Department{" +
                "dep_uuid='" + dep_uuid + '\'' +
                ", course=" + course +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}

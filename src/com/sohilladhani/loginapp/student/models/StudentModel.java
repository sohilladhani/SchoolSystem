package com.sohilladhani.loginapp.student.models;

import com.sohilladhani.loginapp.student.dao.StudentDAO;
import com.sohilladhani.loginapp.student.entities.Student;

import java.util.List;

public class StudentModel {
    private StudentDAO studentDAO;

    public StudentModel(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
        setup(studentDAO);
        connect(studentDAO);
    }

    private void setup(StudentDAO studentDAO) {
        try {
            studentDAO.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connect(StudentDAO studentDAO) {
        try {
            studentDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            studentDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudent(String fName, String lName, String email, String DOB) {
        Student student = new Student();
        student.setFName(fName);
        student.setLName(lName);
        student.setEmail(email);
        student.setDOB(DOB);

        this.studentDAO.insertStudent(student);
    }

    public List<Student> findAllStudents() {
        return this.studentDAO.findAll();
    }
}

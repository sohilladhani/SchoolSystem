package com.sohilladhani.loginapp.student.dao;

import com.sohilladhani.loginapp.student.entities.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.sqlite.SQLiteDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class SQLiteStudentDAO implements StudentDAO {

    private Connection connection;
    private SQLiteDataSource sqLiteDataSource;
    private QueryRunner dbAccess;

    @Override
    public void setup() throws Exception {
        String connectionString = "jdbc:sqlite:schoolsystem.sqlite";
        dbAccess = new QueryRunner();
        sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl(connectionString);
    }

    @Override
    public void connect() throws Exception {
        connection = sqLiteDataSource.getConnection();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public boolean isConnected() throws Exception {
        try {
            return !connection.isClosed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public long insertStudent(Student student) {
        try {
            return dbAccess.insert(connection, "INSERT INTO students (fname, lname, email, " +
                            "dob) VALUES (?,?,?,?)", new ScalarHandler<Integer>(),
                    student.getFName(),
                    student.getLName(),
                    student.getEmail(), student.getDOB());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean updateStudent(Student student) {
        try {
            dbAccess.update(connection, "UPDATE students SET fname=?, lname=?, email=?, dob=?",
                    student.getFName(), student.getLName(), student.getEmail(), student.getDOB());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteStudent(Student student) {
        try {
            dbAccess.update(connection, "DELETE FROM students where ID=?", student.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Student findStudentById(int ID) {
        try {
            return dbAccess.query(connection, "SELECT * FROM students where ID=?",
                    new BeanHandler<>(Student.class), ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        try {
            return dbAccess.query(connection, "SELECT * FROM students",
                    new BeanListHandler<>(Student.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

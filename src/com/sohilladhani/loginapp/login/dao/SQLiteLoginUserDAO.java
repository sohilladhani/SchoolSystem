package com.sohilladhani.loginapp.login.dao;

import com.sohilladhani.loginapp.login.entities.LoginUser;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.util.List;

public class SQLiteLoginUserDAO implements LoginUserDAO {

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
    public long insertLoginUser(LoginUser loginUser) {
        System.out.println("not implemented");
        return 0;
    }

    @Override
    public boolean updateLoginUser(LoginUser loginUser) {
        System.out.println("not implemented");
        return false;
    }

    @Override
    public boolean deleteLoginUser(LoginUser loginUser) {
        System.out.println("not implemented");
        return false;
    }

    @Override
    public LoginUser searchUser(String userName, String password, String division) {
        try {
            return dbAccess.query(connection, "select * FROM login where username=? " +
                            "AND password=? AND " +
                            "division=?",
                    new BeanHandler<>(LoginUser.class), userName, password, division);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LoginUser> findAll() {
        System.out.println("not implemented");
        return null;
    }
}

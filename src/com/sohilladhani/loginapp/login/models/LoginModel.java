package com.sohilladhani.loginapp.login.models;

import com.sohilladhani.loginapp.login.dao.LoginUserDAO;
import com.sohilladhani.loginapp.login.entities.LoginUser;

import java.util.List;

public class LoginModel {
    private LoginUserDAO loginUserDAO;

    public LoginModel(LoginUserDAO loginUserDAO) {
        this.loginUserDAO = loginUserDAO;
        setup(loginUserDAO);
        connect(loginUserDAO);
    }

    private void setup(LoginUserDAO loginUserDAO) {
        try {
            loginUserDAO.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connect(LoginUserDAO loginUserDAO) {
        try {
            loginUserDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLoginUser(String userName, String password, String division) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(userName);
        loginUser.setPassword(password);
        loginUser.setDivision(division);

        this.loginUserDAO.insertLoginUser(loginUser);
    }

    public List<LoginUser> findAllLoginUsers() {
        return loginUserDAO.findAll();
    }

    public boolean isUserLoggedIn(String userName, String password, String division) {
        return (loginUserDAO.searchUser(userName, password, division) != null);
    }

    public boolean isConnected() {
        try {
            return loginUserDAO.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        try {
            loginUserDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

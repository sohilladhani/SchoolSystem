package com.sohilladhani.loginapp.login.entities;

public enum option {
    Admin, Student;

    option(){}

    public String value() {
        return name();
    }

    public static option fromValue(String v) {
        return valueOf(v);
    }
}

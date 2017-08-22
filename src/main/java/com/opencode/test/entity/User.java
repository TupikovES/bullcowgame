package com.opencode.test.entity;

public class User {

    private int id;
    private String login;
    private String pass;

    public User() {}

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

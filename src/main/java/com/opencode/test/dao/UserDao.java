package com.opencode.test.dao;

import com.opencode.test.database.Database;
import com.opencode.test.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null)
            instance = new UserDao();
        return instance;
    }

    private Database db;

    private UserDao() {
        db = Database.getInstance();
    }

    public User get(String login, String pass) {
        try {
            PreparedStatement ps = db.getConnection()
                    .prepareStatement("SELECT u.id, u.login FROM user u WHERE u.login = ? AND u.pass = ?");
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = new User(rs.getInt("id"), rs.getString("login"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int save(String login, String pass) {

        try {
            PreparedStatement pr = db.getConnection()
                    .prepareStatement("INSERT INTO USER (LOGIN, PASS) VALUES (?, ?)");
            pr.setString(1, login);
            pr.setString(2, pass);
            int i = pr.executeUpdate();

            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}

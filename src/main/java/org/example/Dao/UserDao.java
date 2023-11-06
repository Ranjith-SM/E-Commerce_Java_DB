package org.example.Dao;

import org.example.DBConnection.DbCon;
import org.example.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String SELECT_USERS = "SELECT email FROM users;";
    private static final String INSERT_USER = "INSERT INTO users (uname, email, pwd) VALUES(?,?,?);";
    private static final String LOGIN = "SELECT id, email, pwd FROM users WHERE email=? and pwd=?";
    static Connection con = null;
    public UserDao() {
        con = DbCon.getconnection();
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(SELECT_USERS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static void addUser(String name, String email, String password) {
        try {
            PreparedStatement statement = con.prepareStatement(INSERT_USER);
            statement.setString(1,name);
            statement.setString(2,email);
            statement.setString(3,password);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User loggeduser(String email, String password) {
        User user = null;
        try {
            PreparedStatement statement = con.prepareStatement(LOGIN);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pwd"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}

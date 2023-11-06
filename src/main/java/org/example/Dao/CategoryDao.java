package org.example.Dao;

import org.example.DBConnection.DbCon;
import org.example.Models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static sun.misc.Version.println;

public class CategoryDao {

    private static final String SELECT_CATEGORY = "SELECT id,cname FROM categories;";
    private static final String INSERT_CATEGORY = "INSERT INTO categories (id,cname) VALUES(?,?);";
    private static final String UPDATE_CATEGORY = "UPDATE categories SET cname = ? WHERE id = ?;";
    static Connection con = null;

    public CategoryDao() {
        con = DbCon.getconnection();
    }

    public static List<Category> getCategory() {
        List<Category> categories = new ArrayList<>();

        try {
            PreparedStatement statement = con.prepareStatement(SELECT_CATEGORY);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCName(rs.getString("cname"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public static void addCategory(int id, String cName) {
        try {
            PreparedStatement statement = con.prepareStatement(INSERT_CATEGORY);
            statement.setInt(1, id);
            statement.setString(2, cName);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editCategory(int id, String cname) {
        try {
            PreparedStatement statement = con.prepareStatement(UPDATE_CATEGORY);
            statement.setString(1,cname);
            statement.setInt(2,id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Category Updated successfully.... Restart / check DB");
    }
}

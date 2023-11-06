package org.example.Dao;

import org.example.DBConnection.DbCon;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.utils.AppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.example.utils.LoadUtils.getCategories;

public class ProductDao {

    private static final String SELECT_PRODUCTS = "SELECT id,title,price,stocks,cid FROM products;";
    private static final String INSERT_PRODUCT = "INSERT INTO products (title,price,stocks,cid,cname) VALUES(?,?,?,?,?);";

    static Connection con=null;

    public ProductDao() {
        con = DbCon.getconnection();
    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();

        try {
            PreparedStatement statement = con.prepareStatement(SELECT_PRODUCTS);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setPrice(rs.getDouble("price"));
                product.setStocks(rs.getInt("stocks"));

                Category pc = null;
                ArrayList<Category> categories = getCategories();
                for (Category category: categories) {
                    if (category.getId() == rs.getInt("cid")) {
                        pc = category;
                    }
                }

                product.setCategory(pc);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public static void addProduct(String title, int price, int stocks, int cid, String cname) {
        try {
            PreparedStatement statement = con.prepareStatement(INSERT_PRODUCT);
            statement.setString(1, title);
            statement.setInt(2, price);
            statement.setInt(3,stocks);
            statement.setInt(4, cid);
            statement.setString(5,cname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

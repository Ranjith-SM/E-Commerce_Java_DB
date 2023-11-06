package org.example.Dao;

import org.example.DBConnection.DbCon;
import org.example.Models.CartProduct;
import org.example.Models.Product;
import org.example.Models.User;
import org.example.utils.AppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDao {
    private static final String INSERT_USERCART = "INSERT INTO cart (uid,pid,pname,pprice,count) VALUES(?,?,?,?,?);";
    private static final String SELECT_USERCART = "SELECT pid,pname,pprice,count FROM cart WHERE uid = ? AND is_Ordered = FALSE ;" ;
    static Connection con = null;

    public CartDao() {
        con = DbCon.getconnection();

    }

    public static void AddCart(User loggedInUser) {
        int uid = loggedInUser.getId();
        int pid = 0;
        String pname = null;
        int pprice = 0;
        int count = 0;
        for (CartProduct cartProduct: loggedInUser.getUserCart().getCartProducts()) {
            pid = cartProduct.getProduct().getId();
            pname = cartProduct.getProduct().getTitle();
            count = cartProduct.getCount();
            pprice = (int) cartProduct.getProduct().getPrice();
        }


        try {
            PreparedStatement statement = con.prepareStatement(INSERT_USERCART);
            statement.setInt(1, uid);
            statement.setInt(2, pid);
            statement.setString(3, pname);
            statement.setInt(4,pprice);
            statement.setInt(5, count);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<CartProduct> getCartProducts(User loggedInUser) {
        ArrayList<CartProduct> cartProducts = new ArrayList<>();

        try {
            PreparedStatement statement = con.prepareStatement(SELECT_USERCART);
            statement.setInt(1,loggedInUser.getId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("pid"));
                product.setTitle(rs.getString("pname"));
                product.setPrice(rs.getInt("pprice"));
                int count = rs.getInt("count");
//                CartProduct cp = null;
//                ArrayList<CartProduct> cproducts  = loggedInUser.getUserCart().getCartProducts();
//                for (CartProduct cp1 : cproducts) {
//                    if (cp1.getProduct().getId() == rs.getInt("pid")) {
//                        cp = cp1;
//                    }
//                }
                cartProducts.add(new CartProduct(product,count));

            }
        } catch (SQLException | AppException e) {
            throw new RuntimeException(e);
        }
        return cartProducts;
    }
}

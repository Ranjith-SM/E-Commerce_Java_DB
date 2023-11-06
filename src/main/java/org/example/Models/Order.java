package org.example.Models;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int id;
    private Date date;
    private ArrayList<Cart> cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Cart> getCartProducts() {
        return cart;
    }

    public void setCartProducts(ArrayList<Cart> cart) {
        this.cart = cart;
    }
}

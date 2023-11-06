package org.example.Models;

import java.util.ArrayList;

public class Cart {
    private int id;
    private ArrayList<CartProduct> cartProducts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(ArrayList<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}

package org.example.Models;

import java.util.ArrayList;

public class User {
    private int id;
    private String Name;
    private String Email;
    private String Password;
    private Role role;
    private Cart userCart;
    private ArrayList<Order> userOrders;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(String s) {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Cart getUserCart() {
        return userCart;
    }

    public void setUserCart(Cart userCart) {
        this.userCart = userCart;
    }

    public ArrayList<Order> getUserOrder() {
        return userOrders;
    }

    public void setUserOrder(ArrayList<Order> userOrder) {
        this.userOrders = userOrder;
    }
}

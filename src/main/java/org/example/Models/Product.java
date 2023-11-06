package org.example.Models;

import org.example.utils.AppException;

public class Product {
    private int id;
    private String title;
    private String desc;
    private double price;
    private int stocks;
    private Category category;

    public Product(int id, String title, String desc, int price, int stocks, Category category) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.stocks = stocks;
        this.category = category;
    }

    public Product() {

    }

    public Product(String product) {
        this.title=product;
    }

    public Product(int id, String title, double price, int stocks, Category productCategory) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stocks = stocks;
        this.category = productCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws AppException {
        if(price<=0) {
            throw new AppException("!!!Price Cannot be Zero!!!");
        }
        this.price = price;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public Category getCategory() {
//        System.out.println(category.getId());
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}

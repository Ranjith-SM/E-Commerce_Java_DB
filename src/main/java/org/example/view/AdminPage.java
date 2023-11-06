package org.example.view;

import org.example.Models.Category;
import org.example.Models.Product;
import org.example.utils.StringUtils;

import java.util.ArrayList;

import static org.example.utils.Utils.println;

public class AdminPage {
    public void printMenu() {
        println(StringUtils.ADMIN_MENU);
    }

    public void categoryMenu() {
        println(StringUtils.CATEGORIES_MENU);
    }

    public void printThankYou() {
        println(StringUtils.THANK_YOU);
    }

    public void printCategories(ArrayList<Category> categories) {
        try {
            println("#---------------------#");
            println(StringUtils.CATEGORY_MENU);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Category category : categories) {
            println(category.getId() + ". " + category.getCName());

        }
    }

    public void printSuccessful() {
        println("#---------------------#");
        println(StringUtils.CATEGORY_ADDED_SUCCESSFULLY);
        println("#---------------------#");
    }

    public void printUsers(ArrayList<String> users) {
        println(StringUtils.REGISTERED_USERS);
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users);
        }
    }

    public void productMenu() {
        println(StringUtils.PRODUCT_MENU);
    }

    public void printProducts(ArrayList<Product> products) {
        try {
            println("#---------------------#");
            println(StringUtils.PRODUCT_MENU);
            println("#---------------------#");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Product product : products) {
            println(product.getId() + ". " + product.getTitle()+ " - Rs."+ product.getPrice());

        }
    }
}

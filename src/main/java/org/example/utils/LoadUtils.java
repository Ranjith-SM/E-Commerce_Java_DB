package org.example.utils;

import org.example.Dao.CategoryDao;
import org.example.Dao.ProductDao;
import org.example.Models.Category;
import org.example.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class LoadUtils {

    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Category> categories = new ArrayList<>();


    public static void load() {

        List<Category> categoryList = CategoryDao.getCategory();
        categories = (ArrayList<Category>) categoryList;


        // get and set categories for the products.
        List<Product> productslist = ProductDao.getProducts();
        products = (ArrayList<Product>) productslist;


    }
    public static void invalidChoice(AppException e) {


    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }


}

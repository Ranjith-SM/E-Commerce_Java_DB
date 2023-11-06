package org.example.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.example.Dao.CategoryDao;
import org.example.Dao.ProductDao;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.controller.impl.IAdminController;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.AdminPage;
import org.example.view.AuthPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.utils.AppInput.enterNumber;
import static org.example.utils.AppInput.enterString;
import static org.example.utils.FileUtil.getCategoriesFile;
import static org.example.utils.FileUtil.getCredentialFile;
import static org.example.utils.LoadUtils.*;
import static org.example.utils.Utils.println;

public class AdminController implements IAdminController {

    private final AdminPage adminPage;
    private final AuthPage authPage;
    CategoryDao categoryDao;

    public AdminController() {
        adminPage = new AdminPage();
        authPage = new AuthPage();
        categoryDao = new CategoryDao();
    }

    @Override
    public void printMenu() {
        adminPage.printMenu();
        int choice;
        try {
            choice = enterNumber(StringUtils.ENTER_CHOICE);
            if (choice == 99) {
                adminPage.printThankYou();
                System.exit(0);
            } else {
                if (choice == 1) {
                    adminPage.categoryMenu();
                    int catChoice = enterNumber(StringUtils.ENTER_CHOICE);
                    if (catChoice == 99) {
                        printMenu();
                    } else if (catChoice == 1) {
                        ArrayList<Category> categories = getCategories();
                        adminPage.printCategories(categories);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        printMenu();
                    } else if (catChoice == 2) {
                        addCategory();
                        printMenu();
                    } else if (catChoice == 3) {
                        //edit category from file...
                        ArrayList<Category> categories = getCategories();
                        adminPage.printCategories(categories);

                        editCategory();
                        printMenu();
                    } else if (catChoice == 4) {
                        //delete category frm file...
                        ArrayList<Category> categories = getCategories();
                        adminPage.printCategories(categories);

                        deleteCategory();
                        printMenu();


                    } else {
                        println(StringUtils.INVALID_CHOICE);
                    }

                //Products-----------
                } else if (choice == 2) {

                    adminPage.productMenu();
                    int productChoice = enterNumber(StringUtils.ENTER_CHOICE);
                    if (productChoice == 99) {
                        printMenu();
                    } else if (productChoice == 1) {
                        ArrayList<Product> Products = getProducts();
                        adminPage.printProducts(Products);
                        printMenu();
                    } else if (productChoice == 2) {
                        addProducts();
                        printMenu();
                    } else if (productChoice == 3) {
                        //edit Products from file...

                    } else if (productChoice == 4) {
                        //delete category frm file...
                    } else {
                        println(StringUtils.INVALID_CHOICE);
                    }

                } else if (choice == 3) {
                    ArrayList<String> users = new ArrayList<>();
                    try {
                        Scanner sc = new Scanner(getCredentialFile());
                        while (sc.hasNext()) {
                            String value = sc.next().trim();
                            if (!value.startsWith("id")) {
                                if (!value.startsWith("01")) {
                                    String[] userArray = value.split(",");
//                                    System.out.println(userArray[0]+userArray[1]+userArray[2]);

                                    users.add(Arrays.toString(userArray));
                                }
                            }
                        }
                        adminPage.printUsers(users);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (choice == 4) {

                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

    private void addProducts() {
        ArrayList<Product> products = getProducts();
        adminPage.printProducts(products);
        String title= null;
        int price=0;
        int stocks =0;
        int cid = 0;
        String cname = null;
        try {
            title = enterString(StringUtils.ENTER_CNAME);
            price =enterNumber("Enter Price Of The Product: ");
            stocks = enterNumber("Enter Number of Stock Available:");
            cid = enterNumber("Enter Category ID: ");
            cname = enterString("Enter Category Name: ");
            ProductDao.addProduct(title,price,stocks,cid,cname);
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Category added successfully");
    }



    private void editCategory() {

        int id = 0;
        String Cname;
        try {
            id = enterNumber(StringUtils.ENTER_ID);
            Cname = enterString(StringUtils.ENTER_CNAME);
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
        CategoryDao.editCategory(id, Cname);


    }

    void deleteCategory() {

        int id = 0;
        try {
            id = enterNumber(StringUtils.ENTER_ID);
        } catch (AppException e) {
            throw new RuntimeException(e);
        }


    }

    private void addCategory() throws AppException {
        ArrayList<Category> categories = getCategories();
        adminPage.printCategories(categories);
        int id = enterNumber(StringUtils.ENTER_ID);
        String CName = enterString(StringUtils.ENTER_CNAME);

        CategoryDao.addCategory(id, CName);
        System.out.println("Category added successfully");
    }
}
package org.example;

import org.example.Dao.CategoryDao;
import org.example.controller.AppController;
import org.example.utils.LoadUtils;

public class Main {
    public static void main(String[] args) {

        AppController appController = new AppController();
//        AdminController adminController = new AdminController();
        LoadUtils.load();

        appController.init();
    }
}
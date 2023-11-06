package org.example.view;

import org.example.Models.Category;
import org.example.utils.StringUtils;

import java.util.ArrayList;

import static org.example.utils.Utils.println;

public class CategoryPage {
    public void printMenu(ArrayList<Category> categories) {
        try {
            println("#---------------------#");
            println(StringUtils.CATEGORY_MENU);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(categories.size());
        for (Category category : categories) {
            println(category.getId() + ". " + category.getCName());

        }
        println(StringUtils.BACK_OPTION);
    }

}

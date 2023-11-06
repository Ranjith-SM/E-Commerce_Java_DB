package org.example.controller;

import org.example.Models.Category;
import org.example.controller.impl.ICategoryController;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.CategoryPage;

import java.util.ArrayList;

import static org.example.utils.AppInput.enterNumber;
import static org.example.utils.LoadUtils.getCategories;
import static org.example.utils.Utils.println;

public class CategoryController implements ICategoryController {
    private final CategoryPage categoryPage;
    private final ProductController productController;
    private final HomeController homeController;

    public CategoryController(HomeController homeController) {
        categoryPage = new CategoryPage();
        productController = new ProductController(homeController);
        this.homeController = homeController;
    }

    @Override
    public void printMenu() {
        ArrayList<Category> categories = getCategories();
        categoryPage.printMenu(categories);

        try {
            int choice = enterNumber(StringUtils.ENTER_CHOICE);

            if (choice == 99) {
                homeController.printMenu();
            } else {
                int validCategoryId = 0;

                for (Category category : categories) {
                    if (category.getId() == choice) {
                        validCategoryId = category.getId();
                        break;
                    }
                }

                if (validCategoryId != 0) {
                    productController.showProducts(validCategoryId);
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    private void invalidChoice(AppException e) {
        println(e.getMessage());
        printMenu();
    }
}

package org.example.controller;

import org.example.Models.Product;
import org.example.controller.impl.IProductController;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.ProductsPage;

import java.util.ArrayList;

import static org.example.utils.AppInput.enterNumber;
import static org.example.utils.LoadUtils.getProducts;
import static org.example.utils.Utils.println;


public class ProductController implements IProductController {

    private final ProductsPage productsPage;
    private final CartController cartController;
    private final HomeController homeController;
    private int categoryId = 0;

    public ProductController(HomeController homeController) {
        productsPage = new ProductsPage();
        this.homeController = homeController;
        cartController = new CartController(homeController);
    }

    @Override
    public void showProducts(int categoryId) {
        this.categoryId = categoryId;
        ArrayList<Product> products = getProducts();

        if (categoryId != 0) {
            ArrayList<Product> categoryProducts = new ArrayList<>();

            for (Product product : products) {
                if (product.getCategory().getId() == categoryId) {
                    categoryProducts.add(product);
                }

            }
            products = categoryProducts;
        }

        productsPage.printProducts(products);

        try {
            int choice = enterNumber(StringUtils.ENTER_CHOICE);
            int validProductId = 0;

            if (choice == 99) {
                homeController.printMenu();
            } else {
                for (Product product : products) {
                    if (product.getId() == choice) {
                        validProductId = product.getId();
                        break;
                    }
                }

                if (validProductId != 0) {
                    cartController.addToCart(validProductId);
                    productsPage.printSuccess();
                    showProducts(categoryId);
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
        showProducts(categoryId);
    }
}

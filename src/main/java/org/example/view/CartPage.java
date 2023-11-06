package org.example.view;

import org.example.Models.CartProduct;
import org.example.utils.StringUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.utils.Utils.println;

public class CartPage {
    public void printEmptyCart() {
        println(StringUtils.EMPTY_CART);
    }

    public void printCart(ArrayList<CartProduct> cartProducts) {
        println(StringUtils.CART);
        double total = 0;
        Set<CartProduct> cartProductSet = cartProducts.stream().collect(Collectors.toSet());

        for (CartProduct cartProduct : cartProductSet) {
            System.out.println(cartProduct.getProduct().getPrice());
            total +=cartProduct.getCount() * cartProduct.getProduct().getPrice();
            println(cartProduct.getProduct().getId() + " -- " + cartProduct.getProduct().getTitle()+ " -- "+cartProduct.getProduct().getPrice() + " :: "+ cartProduct.getCount());
        }
        println(StringUtils.TOTAL_PRICE + total);
    }

    public void printCheckout() {
        println(StringUtils.PRINT_CHECKOUT);
    }

    public void printBack() {
        println(StringUtils.BACK_OPTION);
    }

}

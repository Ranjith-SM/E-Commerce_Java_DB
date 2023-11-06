package org.example.utils;

import java.io.File;

public class FileUtil {
    private static File credentialFile;
    private static File categoriesFile;
    private static File productFile;
    public static File getCredentialFile() {
        if(credentialFile == null) {
            credentialFile = new File("src/main/java/org/example/assets/credentials.csv");
        }
        return credentialFile;
    }
    public static File getCategoriesFile() {
        if(categoriesFile == null) {
            categoriesFile = new File("src/main/java/org/example/assets/Categories.csv");
        }
        return categoriesFile;
    }

    public static File getProductsFile() {
        if(productFile == null) {
            productFile = new File("src/main/java/org/example/assets/Products.csv");
        }
        return productFile;
    }

    public static String getFilePath() {
        return "src/main/java/org/example/assets/";
    }

    public static String getCartFilePath() {
        return "src/main/java/org/example/assets/cartpath/";
    }

    public static String getCsvFilePath() {

        return "src/main/java/org/example/assets/cartpath/csv/";
    }

    public static String getOrderFilePath() {
        return "src/main/java/org/example/assets/ordersPath/";
    }
}

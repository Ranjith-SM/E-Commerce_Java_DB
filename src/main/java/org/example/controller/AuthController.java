package org.example.controller;

import org.example.Dao.CategoryDao;
import org.example.Dao.ProductDao;
import org.example.Dao.UserDao;
import org.example.Models.Role;
import org.example.Models.User;
import org.example.controller.impl.IAuthController;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.AuthPage;
import org.example.view.LoginPage;
import org.example.view.RegisterPage;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static org.example.utils.AppInput.enterNumber;
import static org.example.utils.AppInput.enterString;
import static org.example.utils.FileUtil.getCredentialFile;
import static org.example.utils.UserUtils.setLoggedInUser;
import static org.example.utils.Utils.println;

public class AuthController implements IAuthController {

    private final HomeController homeController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;
    private final AuthPage authPage;
    private final AdminController adminController;

    private final UserDao userDao;
    CategoryDao categoryDao;
    ProductDao productDao;

    public AuthController() {
        authPage = new AuthPage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        adminController = new AdminController();
        homeController = new HomeController(this);
        userDao = new UserDao();
        categoryDao = new CategoryDao();
        productDao = new ProductDao();

    }

    @Override
    public void authMenu() {
        authPage.printAuthMenu();
        int choice;
        try {
            choice = enterNumber(StringUtils.ENTER_CHOICE);
            if (choice == 99) {
                authPage.printThankYou();
                System.exit(0);
            } else {
                if (choice == 1) {
                    login();
                } else if (choice == 2) {
                    register();
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    @Override
    public void login() {
        String email, password;
        email = enterString(StringUtils.ENTER_EMAIL);
        password = enterString(StringUtils.ENTER_PASSWORD);

        User user = validateUser(email, password);
        if (user != null) {
            setLoggedInUser(user);
            if (email.equals("admin@kumaran.com") && password.equals("admin")) {
                adminController.printMenu();
            } else {
                homeController.printMenu();
            }
        } else {
            loginPage.printInvalid();
            authMenu();
        }
    }

    private User validateUser(String email, String password) {
        User loggedUser = UserDao.loggeduser(email, password);
        if (loggedUser.getEmail().equals("admin@kumaran.com")) {
            loggedUser.setRole(Role.ADMIN);
        } else {
            loggedUser.setRole(Role.USER);
        }
        return loggedUser;
    }

    @Override
    public void register() {
        String name, email, password, c_password;
        name = enterString(StringUtils.ENTER_NAME);
        email = enterString(StringUtils.ENTER_EMAIL);
        password = enterString(StringUtils.ENTER_PASSWORD);
        c_password = enterString(StringUtils.ENTER_PASSWORD_AGAIN);

        if (name != null && email != null && password != null) {
            if (password.equals(c_password)) {
                List<User> users = UserDao.getUsers();
                for (User user : users) {
                    if (user.getEmail().equals(email)) {
                        registerPage.emailUsed();
                    }
                }

                UserDao.addUser(name, email, password);

            } else {
                registerPage.passwordMisMatch();
            }

        }
        authMenu();
    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        authMenu();
    }

}

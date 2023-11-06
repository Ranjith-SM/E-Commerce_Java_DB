package org.example.view;

import org.example.utils.StringUtils;

import static org.example.utils.Utils.println;

public class HomePage {
    public void printMenu() {
        println(StringUtils.WELCOME_MESSAGE);
        println(StringUtils.HOME_MENU);
    }
}

package org.example.view;

import org.example.utils.StringUtils;

import static org.example.utils.Utils.println;

public class LoginPage {
    public void printInvalid() {
        try {
            println("#---------------------#");
            println(StringUtils.INVALID_CREDENTIALS);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

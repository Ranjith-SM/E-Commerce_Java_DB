package org.example.view;

import org.example.utils.StringUtils;

import static org.example.utils.Utils.println;

public class WelcomePage {
    public void welcome() {

        try {
            println(StringUtils.WELCOME_MESSAGE);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

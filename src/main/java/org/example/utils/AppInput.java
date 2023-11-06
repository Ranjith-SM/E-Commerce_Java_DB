package org.example.utils;

import static org.example.utils.AppScanner.getSc;

public class AppInput {
    public static String enterString (String msg) {
        Utils.print(msg);
        return getSc().nextLine();
    }
    public static int enterNumber (String msg) throws AppException {
        Utils.print(msg);
        int input= 0;
        try {
            input = Integer.parseInt(getSc().nextLine());
        } catch (Exception e) {
            throw new AppException(StringUtils.INVALID_CHOICE);
        }
        return input;
    }
}

package org.example.utils;

import java.util.Scanner;

public class AppScanner {
    private static Scanner sc;

    public static Scanner getSc() {
        if(sc==null) return sc = new Scanner(System.in);
        return sc;
    }
}

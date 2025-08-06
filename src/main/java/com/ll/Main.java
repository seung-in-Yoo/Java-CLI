package com.ll;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppContext.renew();
        new App().run();
    }
}
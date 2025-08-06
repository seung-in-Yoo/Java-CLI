package com.ll;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class AppTestRunner {

    public static String run(String input) {
        Scanner scanner = new Scanner(input + "\nexit");

        ByteArrayOutputStream output = setOutToByteArray();
        AppContext.renew(scanner);
        new App().run();

        return output.toString();
    }

    static ByteArrayOutputStream setOutToByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream, true));
        return byteArrayOutputStream;
    }
}

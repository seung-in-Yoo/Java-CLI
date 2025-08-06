package com.ll.user;

import java.util.Scanner;

public class UserController {
    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    public UserController(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean addUser(User user) {
        if (userService.findUser(user) != null) return false;
        userService.addUser(user);
        return true;
    }

    public boolean loginUser(User user) {
        if (userService.findUser(user) != null) return true;
        return false;
    }
}

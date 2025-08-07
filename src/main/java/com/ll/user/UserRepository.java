package com.ll.user;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> users = new ArrayList<User>();

    public User findByName(User user) {
        return users.stream().filter(user1 -> user1.getName().equals(user.getName())).findFirst().orElse(null);
    }

    public void save(User user) {
        users.add(user);
    }

    public User findByNameAndPW(User user) {
        return users.stream().filter(user1 -> user1.getName().equals(user.getName()) && user1.getPassword().equals(user.getPassword())).findFirst().orElse(null);
    }
}

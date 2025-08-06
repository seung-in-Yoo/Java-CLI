package com.ll.user;

public class UserService {
    UserRepository userRepository = new UserRepository();

    public User findUser(User user) {
        return userRepository.findByName(user);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User loginUser(User user) {
        return userRepository.findByNameAndPW(user);
    }
}

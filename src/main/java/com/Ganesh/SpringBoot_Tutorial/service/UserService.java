package com.Ganesh.SpringBoot_Tutorial.service;

import com.Ganesh.SpringBoot_Tutorial.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> users = new ArrayList<>();

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }
}

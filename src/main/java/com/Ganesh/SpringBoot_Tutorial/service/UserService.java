package com.Ganesh.SpringBoot_Tutorial.service;

import com.Ganesh.SpringBoot_Tutorial.exception.UserNotFoundException;
import com.Ganesh.SpringBoot_Tutorial.model.User;
import com.Ganesh.SpringBoot_Tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User updateUser(int id, User newUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        existingUser.setName(newUser.getName());
        existingUser.setAge(newUser.getAge());

        return userRepository.save(existingUser);
    }

    public String deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}

package com.Ganesh.SpringBoot_Tutorial.controller;

import com.Ganesh.SpringBoot_Tutorial.dto.UserDTO;
import com.Ganesh.SpringBoot_Tutorial.model.User;
import com.Ganesh.SpringBoot_Tutorial.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public UserDTO addUser(@Valid @RequestBody UserDTO user) {
        return userService.addUser(user);
    }

    @GetMapping("/getUsers")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/getUser/{id}")
    public UserDTO getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}

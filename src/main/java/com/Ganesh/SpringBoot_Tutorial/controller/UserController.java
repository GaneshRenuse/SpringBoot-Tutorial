package com.Ganesh.SpringBoot_Tutorial.controller;

import com.Ganesh.SpringBoot_Tutorial.dto.UserDTO;
import com.Ganesh.SpringBoot_Tutorial.response.ApiResponse;
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
    public ApiResponse<UserDTO> addUser(@Valid @RequestBody UserDTO user) {
        UserDTO savedUser = userService.addUser(user);
        return new ApiResponse<>("success", "User added successfully", savedUser);
    }

    @GetMapping("/getUsers")
    public ApiResponse<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getUsers();
        return new ApiResponse<>("success", "Users fetched successfully", users);
    }

    @GetMapping("/getUser/{id}")
    public ApiResponse<UserDTO> getUser(@PathVariable Integer id) {
        UserDTO user = userService.getUserById(id);
        return new ApiResponse<>("success", "User fetched successfully", user);
    }

    @PutMapping("/updateUser/{id}")
    public ApiResponse<UserDTO> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO user) {
        UserDTO updatedUser = userService.updateUser(id, user);
        return new ApiResponse<>("success", "User updated successfully", updatedUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ApiResponse<>("success", "User deleted successfully", null);
    }
}

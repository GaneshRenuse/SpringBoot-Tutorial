package com.Ganesh.SpringBoot_Tutorial.controller;

import com.Ganesh.SpringBoot_Tutorial.dto.UserDTO;
import com.Ganesh.SpringBoot_Tutorial.response.ApiResponse;
import com.Ganesh.SpringBoot_Tutorial.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "User APIs", description = "Operations related to User Management")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Add a new user")
    @PostMapping("/addUser")
    public ApiResponse<UserDTO> addUser(@Valid @RequestBody UserDTO user) {
        UserDTO savedUser = userService.addUser(user);
        return new ApiResponse<>("success", "User added successfully", savedUser);
    }

    @Operation(summary = "Get all users with pagination and sorting")
    @GetMapping("/getUsers")
    public ApiResponse<List<UserDTO>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        List<UserDTO> users = userService.getUsers(page, size, sortBy);

        return new ApiResponse<>("success", "Users fetched successfully", users);
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/getUser/{id}")
    public ApiResponse<UserDTO> getUser(@PathVariable Integer id) {
        UserDTO user = userService.getUserById(id);
        return new ApiResponse<>("success", "User fetched successfully", user);
    }

    @Operation(summary = "Update user details")
    @PutMapping("/updateUser/{id}")
    public ApiResponse<UserDTO> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO user) {
        UserDTO updatedUser = userService.updateUser(id, user);
        return new ApiResponse<>("success", "User updated successfully", updatedUser);
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/deleteUser/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ApiResponse<>("success", "User deleted successfully", null);
    }
}

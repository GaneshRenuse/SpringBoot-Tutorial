package com.Ganesh.SpringBoot_Tutorial.service;

import com.Ganesh.SpringBoot_Tutorial.dto.UserDTO;
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

    public UserDTO addUser(UserDTO dto) {
        User user = convertToEntity(dto);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return convertToDTO(user);
    }

    public UserDTO updateUser(int id, UserDTO dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        existingUser.setName(dto.getName());
        existingUser.setAge(dto.getAge());

        User updatedUser = userRepository.save(existingUser);
        return convertToDTO(updatedUser);
    }

    public String deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        return dto;
    }

    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        return user;
    }
}

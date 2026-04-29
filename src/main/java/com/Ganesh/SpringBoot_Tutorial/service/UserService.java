package com.Ganesh.SpringBoot_Tutorial.service;

import com.Ganesh.SpringBoot_Tutorial.dto.UserDTO;
import com.Ganesh.SpringBoot_Tutorial.exception.UserNotFoundException;
import com.Ganesh.SpringBoot_Tutorial.model.User;
import com.Ganesh.SpringBoot_Tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public UserDTO addUser(UserDTO dto) {
        logger.info("Adding new user with name: {}", dto.getName());

        User user = convertToEntity(dto);
        User savedUser = userRepository.save(user);

        logger.info("User saved successfully with ID: {}", savedUser.getId());

        return convertToDTO(savedUser);
    }

    public List<UserDTO> getUsers(int page, int size, String sortBy) {

        logger.info("Fetching users page: {}, size: {}, sorted by: {}", page, size, sortBy);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.getContent()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public UserDTO getUserById(int id) {
        logger.info("Fetching user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });

        logger.info("User fetched successfully: {}", user.getName());

        return convertToDTO(user);
    }

    public UserDTO updateUser(int id, UserDTO dto) {
        logger.info("Updating user with ID: {}", id);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found for update with ID: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });

        existingUser.setName(dto.getName());
        existingUser.setAge(dto.getAge());

        User updatedUser = userRepository.save(existingUser);

        logger.info("User updated successfully with ID: {}", id);

        return convertToDTO(updatedUser);
    }

    public String deleteUser(int id) {
        logger.info("Deleting user with ID: {}", id);

        if (!userRepository.existsById(id)) {
            logger.error("User not found for deletion with ID: {}", id);
            throw new UserNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);

        logger.info("User deleted successfully with ID: {}", id);

        return "User Deleted successfully";
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

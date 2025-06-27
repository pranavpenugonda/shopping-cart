package com.example.userService.service;

import java.util.List;
import java.util.Optional;

import com.example.userService.model.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}


package com.example.service;

import java.util.List;

import com.example.model.UserEntity;
public interface UserService {
    List<UserEntity> findAllUsers(); // Fetch all users
    UserEntity findUserById(Long id); // Find user by ID
    UserEntity saveUser(UserEntity user); // Save user
    void deleteUser(Long id); // Delete user by ID
    UserEntity updateUser(Long id, UserEntity user);
}
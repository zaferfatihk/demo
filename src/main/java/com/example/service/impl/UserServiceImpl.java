package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserRepository;
import com.example.model.UserEntity;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll(); // Fetch all users
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElse(null); // Find user by ID
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user); // Save user
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id); // Delete user by ID
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity userDetails) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setId(userDetails.getId());
            user.setEmail(userDetails.getEmail());
            user.setUsername(userDetails.getUsername());
            user.setAddress(userDetails.getAddress());
            user.setCreatedAt(userDetails.getCreatedAt());
            user.setUpdatedAt(userDetails.getUpdatedAt());
            return userRepository.save(user);
        }
        return null;
    }
}
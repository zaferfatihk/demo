package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // findAll() method is provided by JpaRepository
}
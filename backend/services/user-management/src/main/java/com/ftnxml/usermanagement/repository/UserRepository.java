package com.ftnxml.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.usermanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

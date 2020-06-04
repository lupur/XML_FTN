package com.ftnxml.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.usermanagement.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
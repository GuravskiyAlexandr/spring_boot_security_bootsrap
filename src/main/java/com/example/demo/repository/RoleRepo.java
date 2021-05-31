package com.example.demo.repository;

import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Set<Role> findRolesByRoleIn(List<String> roles);
}

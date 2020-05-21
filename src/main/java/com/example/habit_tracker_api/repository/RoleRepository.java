package com.example.habit_tracker_api.repository;


import com.example.habit_tracker_api.model.Role;
import com.example.habit_tracker_api.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
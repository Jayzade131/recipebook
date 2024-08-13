package com.main.recipebook.repository;

import com.main.recipebook.constant.Role;
import com.main.recipebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    User findByRole(Role role);

}

package com.firststrpsspring.demo.repo;

import com.firststrpsspring.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

package com.medisync.repository;

import com.medisync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // חיפוש משתמש לפי אימייל
    List<User> findByRole(String role);        // חיפוש לפי תפקיד
}


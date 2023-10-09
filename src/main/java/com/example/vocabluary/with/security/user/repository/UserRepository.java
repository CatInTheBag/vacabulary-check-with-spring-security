package com.example.vocabluary.with.security.user.repository;

import com.example.vocabluary.with.security.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}

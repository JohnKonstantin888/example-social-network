package com.example.examplesocialnetwork.repository;

import com.example.examplesocialnetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}

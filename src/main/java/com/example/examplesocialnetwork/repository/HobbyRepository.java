package com.example.examplesocialnetwork.repository;

import com.example.examplesocialnetwork.entity.Hobby;
import com.example.examplesocialnetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HobbyRepository extends JpaRepository<Hobby, UUID> {
    @Query("select h from Hobby h where h.user.id = :userId")
    List<Hobby> findAllByUser(UUID userId);
}

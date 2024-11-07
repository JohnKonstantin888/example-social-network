package com.example.examplesocialnetwork.controllers;

import com.example.examplesocialnetwork.core.UserCore;
import com.example.examplesocialnetwork.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/user/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class UserController {
    private final UserCore userCore;

    @GetMapping("users")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        return ResponseEntity.ok(userCore.findAllUsers());
    }


    @GetMapping("get/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable String id) {
        return ResponseEntity.ok(userCore.findUserById(id));
    }

    @PostMapping("register")
    public ResponseEntity<Void> createUser(@RequestBody String user) {
        userCore.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

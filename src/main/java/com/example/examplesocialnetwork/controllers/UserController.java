package com.example.examplesocialnetwork.controllers;

import com.example.examplesocialnetwork.core.UserCore;
import com.example.examplesocialnetwork.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/user/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class UserController {
    private final UserCore userCore;

    @GetMapping("users")
    public List<UserDTO> findAllUsers() {
        return userCore.findAllUsers();
    }


    @GetMapping("get/{id}")
    public UserDTO findUserById(@PathVariable String id) {
        return userCore.findUserById(id);
    }

    @PostMapping("register")
    public UserDTO findPaymentDocuments(@RequestBody String user) {
        return userCore.registerUser(user);
    }
}

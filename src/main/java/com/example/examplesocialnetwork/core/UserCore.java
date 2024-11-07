package com.example.examplesocialnetwork.core;

import com.example.examplesocialnetwork.UserNotFoundException;
import com.example.examplesocialnetwork.dto.UserDTO;
import com.example.examplesocialnetwork.entity.Hobby;
import com.example.examplesocialnetwork.entity.User;
import com.example.examplesocialnetwork.repository.HobbyRepository;
import com.example.examplesocialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCore {
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserDTO dto = user.transformToDTO();
                    dto.getHobbies().addAll(
                            hobbyRepository.findAllByUser(user.getId()).stream()
                                    .map(Hobby::transformToDTO)
                                    .toList()
                    );
                    return dto;
                })
                .toList();
    }

    public UserDTO findUserById(String id) {
        return userRepository.findById(UUID.fromString(id))
                .map(user -> {
            UserDTO dto = user.transformToDTO();
            dto.getHobbies().addAll(
                    hobbyRepository.findAllByUser(user.getId()).stream()
                            .map(Hobby::transformToDTO)
                            .toList()
            );
            return dto;
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public UserDTO registerUser(String user) {
        return null;
    }
}

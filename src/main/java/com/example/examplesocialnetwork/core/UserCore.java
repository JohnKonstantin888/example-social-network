package com.example.examplesocialnetwork.core;

import com.example.examplesocialnetwork.entity.User;
import com.example.examplesocialnetwork.exceptions.CantParseJsonToEntityException;
import com.example.examplesocialnetwork.exceptions.UserNotFoundException;
import com.example.examplesocialnetwork.dto.UserDTO;
import com.example.examplesocialnetwork.entity.Hobby;
import com.example.examplesocialnetwork.repository.HobbyRepository;
import com.example.examplesocialnetwork.repository.UserRepository;
import com.example.examplesocialnetwork.util.Gender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCore {
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;
    private final JsonMapper jsonMapper;

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
    public void createUser(String body) {
        UserDTO dto;
        try {
            dto = jsonMapper.readValue(body, UserDTO.class);
        } catch (JsonProcessingException e) {
            throw new CantParseJsonToEntityException(UserDTO.class, body);
        }
        if (dto != null) {
            User user = null;
            if (StringUtils.isNotBlank(dto.getId())) {
                Optional<User> optionalUser = userRepository.findById(UUID.fromString(dto.getId()));
                if (optionalUser.isPresent()) {
                    user = optionalUser.get();
                }
            } else {
                user = new User();
            }
            assert user != null;
            user = userRepository.save(user
                    .setUsername(dto.getUsername())
                    .setLastName(dto.getLastName())
                    .setBirthDate(dto.getBirthDate() != null ? dto.getBirthDate() : null)
                    .setGender(StringUtils.isNotBlank(dto.getGender()) ? Gender.valueOf(dto.getGender()) : Gender.MALE)
                    .setCity(dto.getCity())
            );
            User finalUser = user;
            hobbyRepository.saveAll(
                    dto.getHobbies().stream()
                            .map(h -> new Hobby()
                                    .setName(h.getName())
                                    .setUser(finalUser))
                            .toList()
            );
        }
    }
}

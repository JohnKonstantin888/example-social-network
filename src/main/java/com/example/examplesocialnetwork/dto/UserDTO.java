package com.example.examplesocialnetwork.dto;

import com.example.examplesocialnetwork.util.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Value
@Builder(toBuilder = true)
@RequiredArgsConstructor
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    String id;
    String username;
    String lastName;
    LocalDate birthDate;
    String gender;
    String city;
    Set<HobbyDTO> hobbies = new HashSet<>();
}

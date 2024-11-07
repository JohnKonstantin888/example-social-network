package com.example.examplesocialnetwork.entity;

import com.example.examplesocialnetwork.dto.UserDTO;
import com.example.examplesocialnetwork.util.DTOTransformer;
import com.example.examplesocialnetwork.util.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ESC_USERS")
public class User implements DTOTransformer<UserDTO> {
    @Id
    @Column(name = "U_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("Первичный ключ")
    private UUID id;

    @Column(name = "U_NAME")
    @Comment("Имя")
    private String username;

    @Column(name = "U_LAST_NAME")
    @Comment("Фамилия")
    private String lastName;

    @Column(name = "U_BIRTH_DATE")
    @Comment("День рождения")
    private LocalDate birthDate;

    @Column(name = "U_GENDER")
    @Enumerated(EnumType.STRING)
    @Comment("Пол")
    private Gender gender;

    @Column(name = "U_CITY")
    @Comment("Город")
    private String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public UserDTO transformToDTO() {
        return UserDTO.builder()
                .id(id.toString())
                .username(username)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender.name())
                .city(city)
                .build();
    }
}

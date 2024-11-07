package com.example.examplesocialnetwork.entity;

import com.example.examplesocialnetwork.dto.HobbyDTO;
import com.example.examplesocialnetwork.util.DTOTransformer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ESC_HOBBIES")
public class Hobby implements DTOTransformer<HobbyDTO> {
    @Id
    @Column(name = "H_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("Первичный ключ")
    private UUID id;

    @Column(name = "H_NAME")
    @Comment("Название хобби")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "H_USER_ID", foreignKey = @ForeignKey(name = "FK_HOBBY_USER"))
    @ToString.Exclude
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hobby hobby = (Hobby) o;
        return Objects.equals(id, hobby.id) && Objects.equals(name, hobby.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public HobbyDTO transformToDTO() {
        return HobbyDTO.builder()
                .name(name)
                .build();
    }
}

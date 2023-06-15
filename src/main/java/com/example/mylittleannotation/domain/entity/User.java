package com.example.mylittleannotation.domain.entity;


import com.example.mylittleannotation.api.controller.dto.UserRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @Column
    private String name;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(UserRequest userRequest) {
        this.pk = null;
        this.name = userRequest.getName();
        this.id = userRequest.getId();
        this.password = userRequest.getPassword();
        this.description = userRequest.getDescription();
        this.role = Role.USER;
    }

}

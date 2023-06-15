package com.example.mylittleannotation.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
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
}

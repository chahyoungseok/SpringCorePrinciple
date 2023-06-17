package com.example.mylittleannotation.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int hits;

    public void setOptimizeHit(int hits) {
        this.hits = hits;
    }
}

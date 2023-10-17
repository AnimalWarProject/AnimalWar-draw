package com.example.draw.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animals")
public class Animal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String grade; // 등급 : 레어, 레전드, 유니크 등등..
    private Integer attackPower;
    private Integer defencePower;
    private Integer life;
    private String type; // Enum 타입으로 받을예정. 개, 고양이 등등
}

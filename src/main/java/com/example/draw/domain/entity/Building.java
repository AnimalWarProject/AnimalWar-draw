package com.example.draw.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "buildings")
public class Building {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingId;
    private String name;
    private Grade grade;
    private Integer attackPower;
    private Integer defencePower;
    private Integer life;
    private Integer woodRate;
    private Integer ironRate;
    private Integer foodRate;
    private String imagePath;

    private BuildingType buildingType;


//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userUUID")
//    private List<UserBuilding> userBuildings;
}

package com.example.draw.repository;

import com.example.draw.domain.entity.Building;
import com.example.draw.domain.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DrawBuildingsRepository extends JpaRepository<Building, Long> {

    @Query("select b from Building b where b.grade=:grade ORDER BY RAND() LIMIT 1")
    Building pick(Grade grade);
}

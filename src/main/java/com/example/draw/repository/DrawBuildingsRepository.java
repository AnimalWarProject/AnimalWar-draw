package com.example.draw.repository;

import com.example.draw.domain.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DrawBuildingsRepository extends JpaRepository<Building, Long> {

    @Query("select b from Building b where b.grade='LEGEND'ORDER BY RAND() LIMIT 1")
    Building legendPick();

    @Query("select b from Building b where b.grade='UNIQUE'ORDER BY RAND() LIMIT 1")
    Building uniquePick();

    @Query("select b from Building b where b.grade='SUPERRARE'ORDER BY RAND() LIMIT 1")
    Building sRPick();

    @Query("select b from Building b where b.grade='RARE'ORDER BY RAND() LIMIT 1")
    Building rarePick();

    @Query("select b from Building b where b.grade='NORMAL'ORDER BY RAND() LIMIT 1")
    Building normalPick();

}

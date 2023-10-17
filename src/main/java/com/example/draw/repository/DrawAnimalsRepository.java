package com.example.draw.repository;

import com.example.draw.domain.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DrawAnimalsRepository extends JpaRepository<Animal, Long> {

    @Query("select a from Animal a where a.grade='LEGEND'ORDER BY RAND() LIMIT 1")
    Animal legendPick();

    @Query("select a from Animal a where a.grade='UNIQUE'ORDER BY RAND() LIMIT 1")
    Animal uniquePick();

    @Query("select a from Animal a where a.grade='SUPERRARE'ORDER BY RAND() LIMIT 1")
    Animal sRPick();

    @Query("select a from Animal a where a.grade='RARE'ORDER BY RAND() LIMIT 1")
    Animal rarePick();

    @Query("select a from Animal a where a.grade='NORMAL'ORDER BY RAND() LIMIT 1")
    Animal normalPick();

}

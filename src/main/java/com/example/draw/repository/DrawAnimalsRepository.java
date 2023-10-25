package com.example.draw.repository;

import com.example.draw.domain.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DrawAnimalsRepository extends JpaRepository<Animal, Long> {

    @Query("select a from Animal a where a.grade=:grade ORDER BY RAND() LIMIT 1")
    Animal pick(String grade);

}

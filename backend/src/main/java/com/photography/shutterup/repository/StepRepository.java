package com.photography.shutterup.repository;

import com.photography.shutterup.model.Step;
import com.photography.shutterup.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Long> {
    List<Step> findByTutorial(Tutorial tutorial);
}

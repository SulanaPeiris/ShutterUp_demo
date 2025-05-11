package com.photography.shutterup.repository;

import com.photography.shutterup.model.Tutorial;
import com.photography.shutterup.model.TutorialProgress;
import com.photography.shutterup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorialProgressRepository extends JpaRepository<TutorialProgress, Long> {
    Optional<TutorialProgress> findByUserAndTutorial(User user, Tutorial tutorial);
}

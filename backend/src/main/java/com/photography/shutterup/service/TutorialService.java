package com.photography.shutterup.service;

import com.photography.shutterup.model.Tutorial;
import com.photography.shutterup.model.User;

import java.util.List;

public interface TutorialService {
    Tutorial createTutorial(Tutorial tutorial, User creator);
    Tutorial getTutorialById(Long id);
    List<Tutorial> getTutorialsByUser(Long userId);
    List<Tutorial> getTutorialsByCategory(String category);
    List<Tutorial> getAllTutorials();
    void deleteTutorial(Long id);
}

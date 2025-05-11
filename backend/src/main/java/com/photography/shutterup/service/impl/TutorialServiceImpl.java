package com.photography.shutterup.service.impl;

import com.photography.shutterup.exception.ResourceNotFoundException;
import com.photography.shutterup.model.Tutorial;
import com.photography.shutterup.model.User;
import com.photography.shutterup.repository.TutorialRepository;
import com.photography.shutterup.service.TutorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;

    @Override
    public Tutorial createTutorial(Tutorial tutorial, User creator) {
        if (creator.getRole() != User.Role.VERIFIED_USER) {
            throw new RuntimeException("Only verified users can create tutorials.");
        }
        tutorial.setUser(creator);
        return tutorialRepository.save(tutorial);
    }

    @Override
    public Tutorial getTutorialById(Long id) {
        return tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorial not found with id: " + id));
    }

    @Override
    public List<Tutorial> getTutorialsByUser(Long userId) {
        return tutorialRepository.findByUserId(userId);
    }

    @Override
    public List<Tutorial> getTutorialsByCategory(String category) {
        return tutorialRepository.findByCategory(category);
    }

    @Override
    public void deleteTutorial(Long id) {
        Tutorial tutorial = getTutorialById(id);
        tutorialRepository.delete(tutorial);
    }


    @Override
    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }
}

package com.photography.shutterup.service.impl;

import com.photography.shutterup.exception.ResourceNotFoundException;
import com.photography.shutterup.model.*;
import com.photography.shutterup.repository.TutorialProgressRepository;
import com.photography.shutterup.repository.TutorialRepository;
import com.photography.shutterup.service.TutorialProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorialProgressServiceImpl implements TutorialProgressService {

    private final TutorialProgressRepository progressRepository;
    private final TutorialRepository tutorialRepository;

    @Override
    public TutorialProgress enroll(User user, Tutorial tutorial) {
        return progressRepository.findByUserAndTutorial(user, tutorial)
                .orElseGet(() -> {
                    TutorialProgress progress = TutorialProgress.builder()
                            .user(user)
                            .tutorial(tutorial)
                            .completedStepIds(new ArrayList<>())
                            .completed(false)
                            .build();
                    return progressRepository.save(progress);
                });
    }

    @Override
    public TutorialProgress markStepComplete(User user, Tutorial tutorial, Long stepId) {
        TutorialProgress progress = progressRepository.findByUserAndTutorial(user, tutorial)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        if (!progress.getCompletedStepIds().contains(stepId)) {
            progress.getCompletedStepIds().add(stepId);
        }

        int totalSteps = tutorial.getSteps().size();
        if (progress.getCompletedStepIds().size() >= totalSteps) {
            progress.setCompleted(true);
            progress.setCompletedAt(LocalDateTime.now());
        }

        return progressRepository.save(progress);
    }

    @Override
    public TutorialProgress getProgress(User user, Tutorial tutorial) {
        return progressRepository.findByUserAndTutorial(user, tutorial)
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found"));
    }
}

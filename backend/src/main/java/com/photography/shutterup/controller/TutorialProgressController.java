package com.photography.shutterup.controller;

import com.photography.shutterup.model.Tutorial;
import com.photography.shutterup.model.TutorialProgress;
import com.photography.shutterup.model.User;
import com.photography.shutterup.service.TutorialProgressService;
import com.photography.shutterup.service.TutorialService;
import com.photography.shutterup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutorial-progress")
@RequiredArgsConstructor
public class TutorialProgressController {

    private final TutorialProgressService progressService;
    private final TutorialService tutorialService;
    private final UserService userService;

    @PostMapping("/enroll")
    public TutorialProgress enroll(
            @RequestParam Long userId,
            @RequestParam Long tutorialId
    ) {
        User user = userService.getUserById(userId);
        Tutorial tutorial = tutorialService.getTutorialById(tutorialId);
        return progressService.enroll(user, tutorial);
    }

    @PatchMapping("/complete-step")
    public TutorialProgress completeStep(
            @RequestParam Long userId,
            @RequestParam Long tutorialId,
            @RequestParam Long stepId
    ) {
        User user = userService.getUserById(userId);
        Tutorial tutorial = tutorialService.getTutorialById(tutorialId);
        return progressService.markStepComplete(user, tutorial, stepId);
    }

    @GetMapping
    public TutorialProgress getProgress(
            @RequestParam Long userId,
            @RequestParam Long tutorialId
    ) {
        User user = userService.getUserById(userId);
        Tutorial tutorial = tutorialService.getTutorialById(tutorialId);
        return progressService.getProgress(user, tutorial);
    }
}

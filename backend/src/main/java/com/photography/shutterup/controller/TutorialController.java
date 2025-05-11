package com.photography.shutterup.controller;

import com.photography.shutterup.dto.StepDTO;
import com.photography.shutterup.dto.TutorialRequestDTO;
import com.photography.shutterup.dto.TutorialResponseDTO;
import com.photography.shutterup.model.Step;
import com.photography.shutterup.model.Tutorial;
import com.photography.shutterup.model.User;
import com.photography.shutterup.service.TutorialService;
import com.photography.shutterup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tutorials")
@RequiredArgsConstructor
public class TutorialController {

    private final TutorialService tutorialService;
    private final UserService userService;

    @PostMapping("/create")
    public Tutorial createTutorial(@RequestBody TutorialRequestDTO dto) {
        User creator = userService.getUserById(dto.getCreatorId());
        if (creator.getRole() != User.Role.VERIFIED_USER) {
            throw new RuntimeException("Only verified users can create tutorials.");
        }

        Tutorial tutorial = Tutorial.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .templateType(dto.getTemplateType())
                .category(dto.getCategory())
                .user(creator)
                .published(true)
                .build();

        List<Step> steps = dto.getSteps().stream().map(stepDto ->
                Step.builder()
                        .title(stepDto.getTitle())
                        .description(stepDto.getDescription())
                        .imageUrl(stepDto.getImageUrl())
                        .stepOrder(stepDto.getStepOrder())
                        .tutorial(tutorial)
                        .build()
        ).toList();

        tutorial.setSteps(steps);
        return tutorialService.createTutorial(tutorial, creator);
    }

    @GetMapping("/{id}")
    public TutorialResponseDTO getById(@PathVariable Long id) {
        Tutorial tutorial = tutorialService.getTutorialById(id);

        Long userId = tutorial.getUser() != null ? tutorial.getUser().getId() : null;

        return TutorialResponseDTO.builder()
                .id(tutorial.getId())
                .title(tutorial.getTitle())
                .content(tutorial.getContent())
                .templateType(tutorial.getTemplateType())
                .category(tutorial.getCategory())
                .userId(userId)
                .createdAt(tutorial.getCreatedAt())
                .steps(
                        tutorial.getSteps() != null
                                ? tutorial.getSteps().stream().map(step ->
                                StepDTO.builder()
                                        .title(step.getTitle())
                                        .description(step.getDescription())
                                        .imageUrl(step.getImageUrl())
                                        .stepOrder(step.getStepOrder())
                                        .build()
                        ).collect(Collectors.toList())
                                : List.of()
                )
                .build();
    }

    @GetMapping
    public List<TutorialResponseDTO> getAll() {
        return tutorialService.getAllTutorials().stream().map(tut ->
                TutorialResponseDTO.builder()
                        .id(tut.getId())
                        .title(tut.getTitle())
                        .content(tut.getContent())
                        .templateType(tut.getTemplateType())
                        .category(tut.getCategory())
                        .userId(tut.getUser() != null ? tut.getUser().getId() : null)
                        .createdAt(tut.getCreatedAt())
                        .build()
        ).toList();
    }

    @DeleteMapping("/{id}")
    public void deleteTutorial(@PathVariable Long id) {
        tutorialService.deleteTutorial(id);
    }

    @GetMapping("/user/{userId}")
    public List<Tutorial> getByUser(@PathVariable Long userId) {
        return tutorialService.getTutorialsByUser(userId);
    }

    @GetMapping("/category/{category}")
    public List<Tutorial> getByCategory(@PathVariable String category) {
        return tutorialService.getTutorialsByCategory(category);
    }
}

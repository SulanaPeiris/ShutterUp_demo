package com.photography.shutterup.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TutorialResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String templateType;
    private String category;
    private Long userId;
    private LocalDateTime createdAt;
    private List<StepDTO> steps; // ✅ required for .steps(...) in the controller
}

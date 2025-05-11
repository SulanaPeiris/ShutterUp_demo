package com.photography.shutterup.dto;

import lombok.Data;
import java.util.List;

@Data
public class TutorialRequestDTO {
    private String title;
    private String content;
    private String category;
    private String templateType;
    private Long creatorId;
    private List<StepDTO> steps;
}

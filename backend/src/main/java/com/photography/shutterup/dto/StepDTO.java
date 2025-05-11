package com.photography.shutterup.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StepDTO {
    private String title;
    private String description;
    private String imageUrl;
    private int stepOrder;
}

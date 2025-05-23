package com.photography.shutterup.dto;

import com.photography.shutterup.model.User.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private String name;
    private String bio;
    private Role role;
    private Boolean isBanned;
    private LocalDateTime createdAt;
}

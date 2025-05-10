package com.photography.shutterup.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
    private String name;
    private String bio;
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isBanned = false;
    private int followers = 0;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public enum Role {
        USER,
        VERIFIED_USER,
        ADMIN
    }

    public void incrementFollowers() {
        this.followers++;
        if (this.role == Role.USER && this.followers >= 10) {
            this.role = Role.VERIFIED_USER;
        }
    }
}

package com.photography.shutterup.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Many-to-many relationship for followers
    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>(); // Initialize as an empty set to avoid null

    // Many-to-many relationship for following
    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followee_id")
    )
    private Set<User> following = new HashSet<>(); // Initialize as an empty set

    public enum Role {
        USER,
        VERIFIED_USER,
        ADMIN
    }

    // Method to promote to verified user once followers reach 10
    public void promoteToVerifiedIfEligible() {
        if (this.role == Role.USER && this.followers.size() >= 10) {
            this.role = Role.VERIFIED_USER;
        }
    }
}

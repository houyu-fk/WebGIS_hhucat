package com.hhu.cat.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 64)
    private String openid;

    @Column(nullable = false, length = 32)
    private String nickname;

    @Column(length = 255)
    private String avatarUrl;

    @Column(length = 10)
    private String role;   // user / volunteer / admin

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
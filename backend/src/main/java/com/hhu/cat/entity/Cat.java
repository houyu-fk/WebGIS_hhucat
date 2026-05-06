package com.hhu.cat.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(length = 32)
    private String breed;

    @Column(length = 2)
    private String gender;

    @Column(length = 16)
    private String age;

    @Column(columnDefinition = "TEXT")
    private String characterDesc;

    @Column(length = 8)
    private String neutered;

    @Column(length = 8)
    private String vaccinated;

    @Column(length = 8)
    private String healthStatus;

    @Column(columnDefinition = "TEXT")
    private String medicalRecords;

    @Column(nullable = false, length = 20)
    private String campus;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Column(length = 255)
    private String locationDesc;

    private LocalDateTime lastSeen;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 在 class Cat 里面添加
    @Column(name = "photo_url", length = 255)
    private String photoUrl;     // 猫咪照片URL

    @Column(name = "image_path", length = 500)
    private String imagePath;

    @Column(name = "image_filename", length = 255)
    private String imageFilename;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

// Getter 和 Setter（Lombok @Data 会自动生成）

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
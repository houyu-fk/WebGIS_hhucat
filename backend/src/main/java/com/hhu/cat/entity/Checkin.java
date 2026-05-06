package com.hhu.cat.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "checkins")
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;      // 改为 Integer

    @Column(name = "cat_id", nullable = false)
    private Integer catId;       // 改为 Integer

    @Column(length = 255)
    private String photoUrl;

    @Column(length = 500)
    private String notes;

    @Column(name = "checkin_time")
    private LocalDateTime checkinTime;

    @PrePersist
    protected void onCreate() {
        checkinTime = LocalDateTime.now();
    }
}

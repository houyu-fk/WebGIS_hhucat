package com.hhu.cat.repository;

import com.hhu.cat.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    List<Announcement> findByType(String type);
    List<Announcement> findByIsPinnedTrueOrderByCreatedAtDesc();
}
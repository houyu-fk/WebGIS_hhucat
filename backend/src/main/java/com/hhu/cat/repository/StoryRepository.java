package com.hhu.cat.repository;

import com.hhu.cat.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    List<Story> findByCatIdOrderByCreatedAtDesc(Integer catId);
}
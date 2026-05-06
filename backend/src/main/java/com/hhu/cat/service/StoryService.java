package com.hhu.cat.service;

import com.hhu.cat.entity.Story;
import com.hhu.cat.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    public List<Story> findAll() {
        return storyRepository.findAll();
    }

    public List<Story> findByCatId(Integer catId) {
        return storyRepository.findByCatIdOrderByCreatedAtDesc(catId);
    }

    public Story save(Story story) {
        return storyRepository.save(story);
    }
}
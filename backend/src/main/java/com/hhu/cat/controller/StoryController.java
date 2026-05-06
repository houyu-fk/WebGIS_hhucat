package com.hhu.cat.controller;

import com.hhu.cat.entity.Story;
import com.hhu.cat.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@CrossOrigin("*")
public class StoryController {

    @Autowired
    private StoryService storyService;

    // 获取所有故事（前端 stories.html 需要这个）
    @GetMapping
    public ResponseEntity<List<Story>> getAllStories() {
        return ResponseEntity.ok(storyService.findAll());
    }

    // 根据猫咪ID获取故事（detail.html 需要这个）
    @GetMapping("/cat/{catId}")
    public ResponseEntity<List<Story>> getStoriesByCat(@PathVariable Integer catId) {
        return ResponseEntity.ok(storyService.findByCatId(catId));
    }

    // 发布故事
    @PostMapping
    public ResponseEntity<Story> createStory(@RequestBody Story story) {
        return ResponseEntity.ok(storyService.save(story));
    }
}
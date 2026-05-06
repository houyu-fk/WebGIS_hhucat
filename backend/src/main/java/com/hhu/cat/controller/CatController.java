package com.hhu.cat.controller;

import com.hhu.cat.entity.Cat;
import com.hhu.cat.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cats")
@CrossOrigin(origins = "*")  // 允许前端跨域访问
public class CatController {
    @Autowired
    private CatService catService;

@GetMapping
public ResponseEntity<List<Cat>> getAllCats() {
    List<Cat> cats = catService.findAll();
    
    for (Cat cat : cats) {
        if (cat.getImageFilename() != null) {
            // 关键：生成前端可访问的URL
            cat.setImageUrl("/cat_images/" + cat.getImageFilename());
        }
    }
    return ResponseEntity.ok(cats);
}

@GetMapping("/{id}")
public ResponseEntity<Cat> getCatById(@PathVariable Integer id) {
    return catService.findById(id)
            .map(cat -> {
                if (cat.getImageFilename() != null) {
                    cat.setImageUrl("/cat_images/" + cat.getImageFilename());
                }
                return ResponseEntity.ok(cat);
            })
            .orElse(ResponseEntity.notFound().build());
}

    // 新增猫咪
    @PostMapping
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        Cat savedCat = catService.save(cat);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCat);
    }

    // 更新猫咪信息
    @PutMapping("/{id}")
    public ResponseEntity<Cat> updateCat(@PathVariable Integer id, @RequestBody Cat cat) {
        return ResponseEntity.ok(catService.update(id, cat));
    }

    // 删除猫咪
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable Integer id) {
        catService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
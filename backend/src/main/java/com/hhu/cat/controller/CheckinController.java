package com.hhu.cat.controller;

import com.hhu.cat.entity.Checkin;
import com.hhu.cat.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/checkins")
@CrossOrigin(origins = "*")
public class CheckinController {
    @Autowired
    private CheckinService checkinService;

    // 获取所有打卡记录
    @GetMapping
    public ResponseEntity<List<Checkin>> getAllCheckins() {
        return ResponseEntity.ok(checkinService.findAll());
    }

    // 根据打卡记录ID获取
    @GetMapping("/{id}")
    public ResponseEntity<Checkin> getCheckinById(@PathVariable Integer id) {
        return checkinService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 新增打卡（前端只需传 userId 和 catId）
    @PostMapping
    public ResponseEntity<Checkin> createCheckin(@RequestBody Checkin checkin) {
        Checkin savedCheckin = checkinService.save(checkin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCheckin);
    }

    // 更新打卡记录（通常只修改描述和照片）
    @PutMapping("/{id}")
    public ResponseEntity<Checkin> updateCheckin(@PathVariable Integer id, @RequestBody Checkin checkin) {
        return ResponseEntity.ok(checkinService.update(id, checkin));
    }

    // 删除打卡记录
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckin(@PathVariable Integer id) {
        checkinService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
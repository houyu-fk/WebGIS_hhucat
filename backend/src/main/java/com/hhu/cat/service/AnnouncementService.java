package com.hhu.cat.service;

import com.hhu.cat.entity.Announcement;
import com.hhu.cat.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement> findAll() {
        return announcementRepository.findAll();
    }

    public Optional<Announcement> findById(Integer id) {
        return announcementRepository.findById(id);
    }

    public Announcement save(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    public Announcement update(Integer id, Announcement announcementDetails) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("公告不存在, ID: " + id));
        announcement.setTitle(announcementDetails.getTitle());
        announcement.setContent(announcementDetails.getContent());
        announcement.setType(announcementDetails.getType());
        announcement.setIsPinned(announcementDetails.getIsPinned());
        return announcementRepository.save(announcement);
    }

    public void delete(Integer id) {
        announcementRepository.deleteById(id);
    }
}
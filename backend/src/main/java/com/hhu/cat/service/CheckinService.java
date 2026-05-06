package com.hhu.cat.service;

import com.hhu.cat.entity.Checkin;
import com.hhu.cat.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CheckinService {
    @Autowired
    private CheckinRepository checkinRepository;

    public List<Checkin> findAll() {
        return checkinRepository.findAll();
    }

    public Optional<Checkin> findById(Integer id) {
        return checkinRepository.findById(id);
    }

    public Checkin save(Checkin checkin) {
        return checkinRepository.save(checkin);
    }

    public Checkin update(Integer id, Checkin checkinDetails) {
        Checkin checkin = checkinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("打卡记录不存在, ID: " + id));
        checkin.setPhotoUrl(checkinDetails.getPhotoUrl());
        checkin.setNotes(checkinDetails.getNotes());
        return checkinRepository.save(checkin);
    }

    public void delete(Integer id) {
        checkinRepository.deleteById(id);
    }
}
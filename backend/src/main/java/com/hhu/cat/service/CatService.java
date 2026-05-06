package com.hhu.cat.service;

import com.hhu.cat.entity.Cat;
import com.hhu.cat.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;

    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    public Optional<Cat> findById(Integer id) {
        return catRepository.findById(id);
    }

    public Cat save(Cat cat) {
        return catRepository.save(cat);
    }

    public Cat update(Integer id, Cat catDetails) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("猫咪不存在, ID: " + id));
        cat.setName(catDetails.getName());
        cat.setBreed(catDetails.getBreed());
        cat.setGender(catDetails.getGender());
        cat.setAge(catDetails.getAge());
        cat.setCharacterDesc(catDetails.getCharacterDesc());
        cat.setNeutered(catDetails.getNeutered());
        cat.setVaccinated(catDetails.getVaccinated());
        cat.setHealthStatus(catDetails.getHealthStatus());
        cat.setMedicalRecords(catDetails.getMedicalRecords());
        cat.setCampus(catDetails.getCampus());
        cat.setLongitude(catDetails.getLongitude());
        cat.setLatitude(catDetails.getLatitude());
        cat.setLocationDesc(catDetails.getLocationDesc());
        cat.setLastSeen(catDetails.getLastSeen());
        return catRepository.save(cat);
    }

    public void delete(Integer id) {
        catRepository.deleteById(id);
    }
}
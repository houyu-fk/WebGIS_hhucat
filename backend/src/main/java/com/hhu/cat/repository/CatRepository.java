package com.hhu.cat.repository;

import com.hhu.cat.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
    List<Cat> findByCampus(String campus);
    List<Cat> findByNeutered(String neutered);
    List<Cat> findByNameContaining(String keyword);
}
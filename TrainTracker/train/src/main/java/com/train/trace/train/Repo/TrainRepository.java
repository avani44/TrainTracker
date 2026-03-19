package com.train.trace.train.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.train.trace.train.Entity.Train;   // ✅ ADD THIS

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    
}

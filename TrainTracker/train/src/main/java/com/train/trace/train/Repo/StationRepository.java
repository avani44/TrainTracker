package com.train.trace.train.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.train.trace.train.Entity.Station;  

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    
}
package com.fif.services.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fif.entity.Log;

@Repository
public interface DatabaseLogJpaRepository extends JpaRepository<Log, String> {
    
}

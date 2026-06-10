package com.fif.services.impl;

import com.fif.entity.Log;
import com.fif.services.LogRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DatabaseLogRepository implements LogRepository {

    @Autowired
    private DatabaseLogJpaRepository jpaRepository;

    @Override
    public List<Log> queryAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Log get(String id) {
        return jpaRepository.findById(id).orElse(null);
    }

    @Override
    public Log save(Log log) {
        return jpaRepository.save(log);
    }

    @Override
    public void delete(Log log) {
        jpaRepository.delete(log);
    }
}

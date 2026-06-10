package com.fif.services;

import com.fif.entity.Log;
import java.util.List;

public interface LogRepository {
    
    List<Log> queryAll();
    
    Log get(String id);
    
    Log save(Log log);
    
    void delete(Log log);
}
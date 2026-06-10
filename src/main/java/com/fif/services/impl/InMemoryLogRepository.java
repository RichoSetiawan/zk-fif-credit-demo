package com.fif.services.impl;

import com.fif.entity.Log;
import com.fif.services.LogRepository;
import com.fif.util.MyGenerator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryLogRepository implements LogRepository {

    private final ConcurrentMap<String, Log> logStore = new ConcurrentHashMap<>();
    private final MyGenerator idGenerator = new MyGenerator();

    @Override
    public List<Log> queryAll() {
        return new ArrayList<>(logStore.values());
    }

    @Override
    public Log get(String id) {
        return logStore.get(id);
    }

    @Override
    public Log save(Log log) {
        if (log.getId() == null || log.getId().isEmpty()) {
            String generatedId = (String) idGenerator.generate(null, null);
            log.setId(generatedId);
        }
        
        if (log.getDate() == null) {
            log.setDate(new Date());
        }
        
        Log logCopy = createCopy(log);
        logStore.put(logCopy.getId(), logCopy);
        return logCopy;
    }

    @Override
    public void delete(Log log) {
        if (log != null && log.getId() != null) {
            logStore.remove(log.getId());
        }
    }

    private Log createCopy(Log original) {
        Log copy = new Log();
        copy.setId(original.getId());
        copy.setMessage(original.getMessage());
        copy.setDate(original.getDate() != null ? new Date(original.getDate().getTime()) : null);
        return copy;
    }

    public void clear() {
        logStore.clear();
    }

    public int size() {
        return logStore.size();
    }
}
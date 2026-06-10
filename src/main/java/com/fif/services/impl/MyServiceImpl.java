package com.fif.services.impl;

import com.fif.entity.Log;
import com.fif.services.LogRepository;
import com.fif.services.MyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("myService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyServiceImpl implements  MyService {

	@Autowired
	LogRepository logRepository;

	public Log addLog(Log log) {
		return logRepository.save(log);
	}

	public List<Log> getLogs() {
		return logRepository.queryAll();
	}

	public void deleteLog(Log log) {
		logRepository.delete(log);
	}

}

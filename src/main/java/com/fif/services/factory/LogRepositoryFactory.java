package com.fif.services.factory;

import com.fif.services.LogRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LogRepositoryFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private String strategy = "database";

    public enum RepositoryStrategy {
        DATABASE, MEMORY
    }

    public LogRepository createRepository() {
        return createRepository(RepositoryStrategy.valueOf(strategy.toUpperCase()));
    }

    public LogRepository createRepository(RepositoryStrategy strategy) {
        switch (strategy) {
            case MEMORY:
                return (LogRepository) applicationContext.getBean("inMemoryLogRepository");
            case DATABASE:
            default:
                return (LogRepository) applicationContext.getBean("databaseLogRepository");
        }
    }

    public LogRepository createRepository(String strategyName) {
        try {
            RepositoryStrategy strategy = RepositoryStrategy.valueOf(strategyName.toUpperCase());
            return createRepository(strategy);
        } catch (IllegalArgumentException e) {
            // Default to database if invalid strategy provided
            return (LogRepository) applicationContext.getBean("databaseLogRepository");
        }
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
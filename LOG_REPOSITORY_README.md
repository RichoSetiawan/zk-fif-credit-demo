# LogRepository Strategy Pattern Implementation

This implementation provides two strategies for storing Log entities:
1. **Database Strategy** - Uses JPA/Hibernate for database persistence
2. **Memory Strategy** - Uses in-memory storage with ConcurrentHashMap

## Configuration (for Older Spring Versions)

The strategy is configured in `applicationContext.xml`:

```xml
<!-- Repository implementations -->
<bean id="databaseLogRepository" class="com.fif.services.impl.DatabaseLogRepository" />
<bean id="inMemoryLogRepository" class="com.fif.services.impl.InMemoryLogRepository" />

<!-- LogRepository Factory with strategy configuration -->
<bean id="logRepositoryFactory" class="com.fif.services.factory.LogRepositoryFactory">
    <property name="databaseRepository" ref="databaseLogRepository" />
    <property name="inMemoryRepository" ref="inMemoryLogRepository" />
    <property name="strategy" value="database" /> <!-- Change to "memory" for in-memory strategy -->
</bean>

<!-- Primary LogRepository bean - delegates to factory -->
<bean id="logRepository" factory-bean="logRepositoryFactory" factory-method="createRepository" />
```

## Switching Strategies

To switch between strategies, simply change the `strategy` property value in `applicationContext.xml`:

- For database storage: `<property name="strategy" value="database" />`
- For in-memory storage: `<property name="strategy" value="memory" />`

## Usage in Services

Your service classes can now inject the `LogRepository` interface:

```java
@Service("myService")
public class MyServiceImpl implements MyService {
    
    @Autowired
    LogRepository logRepository; // Will use the configured strategy
    
    public Log addLog(Log log) {
        return logRepository.save(log);
    }
    
    public List<Log> getLogs() {
        return logRepository.queryAll();
    }
}
```

## Factory Pattern Usage

You can also use the factory directly for dynamic strategy selection:

```java
@Autowired
private LogRepositoryFactory factory;

// Use specific strategy
LogRepository memoryRepo = factory.createRepository("memory");
LogRepository databaseRepo = factory.createRepository("database");

// Or use enum
LogRepository repo = factory.createRepository(LogRepositoryFactory.RepositoryStrategy.MEMORY);
```

## Key Files

- `LogRepository.java` - Common interface
- `DatabaseLogRepository.java` - Database implementation
- `InMemoryLogRepository.java` - In-memory implementation  
- `LogRepositoryFactory.java` - Factory for strategy selection
- `applicationContext.xml` - XML configuration
package tipolt.andre.spring.services;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

    @Autowired
    private RedisTemplate<String, Object> redisRepository;

    @Value("${app-config.cache.ttl}")
    private Long ttl;

    public void save(String key, Object json) {
        redisRepository.opsForValue().set(key, json);
        redisRepository.expire(key, Duration.ofSeconds(ttl));
    }

    public String get(String key) {
        return (String) redisRepository.opsForValue().get(key);
    }
}

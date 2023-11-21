package tipolt.andre.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

    @Autowired
    private RedisTemplate<String, Object> redisRepository;

    public void save(String key, Object json) {
        redisRepository.opsForValue().set(key, json);
    }

    public String get(String key) {
        return (String) redisRepository.opsForValue().get(key);
    }
}

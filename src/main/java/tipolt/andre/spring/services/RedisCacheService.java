package tipolt.andre.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisCacheService {


    @Autowired
    private RedisTemplate<String, Object> redisRepository;

    public void saveJson(String key, String json) {
        redisRepository.opsForValue().set(key, json);
    }

    public String getJson(String key) {
        return (String) redisRepository.opsForValue().get(key);
    }
}
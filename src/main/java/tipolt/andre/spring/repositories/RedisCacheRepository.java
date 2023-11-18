package tipolt.andre.spring.repositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public class RedisCacheRepository {
    
    @Autowired
    private ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    @Value("${app-config.cache.ttl}")
    private Integer ttl;
    
    public Mono<Boolean> save(String key, Object object){
        return reactiveRedisTemplate.opsForValue()
                .set(key, object)
                .then(reactiveRedisTemplate.expire(key, Duration.ofSeconds(ttl))); // Expiração da Chave]
    }

    public Mono<Object> get(Object object){
        return reactiveRedisTemplate.opsForValue()
                .get(object);
    }

    public Mono<Boolean> existsForKey(String key){
        return reactiveRedisTemplate
                .hasKey(key);
    }
}

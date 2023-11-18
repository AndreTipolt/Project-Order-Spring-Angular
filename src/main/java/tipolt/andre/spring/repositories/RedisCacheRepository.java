package tipolt.andre.spring.repositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class RedisCacheRepository {
    
    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    @Value("${app-config.cache.ttl}")
    private Integer ttl;

    public Mono<Boolean> save(String key, String value){
        return reactiveRedisTemplate.opsForValue()
                .set(key, value)
                .then(reactiveRedisTemplate.expire(key, Duration.ofSeconds(ttl)));
    }

    public Mono<String> get(String key){
        return reactiveRedisTemplate.opsForValue().get(key);
    }

    public Mono<Boolean> existsForKey(String key){
        return reactiveRedisTemplate
                .hasKey(key)
                .onErrorResume(error -> {
                    log.error("Error to query data no Redis");
                    return Mono.just(false);
                });
    }
}
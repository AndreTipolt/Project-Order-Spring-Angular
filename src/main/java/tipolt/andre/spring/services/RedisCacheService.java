package tipolt.andre.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import tipolt.andre.spring.repositories.RedisCacheRepository;

@Slf4j
@Service
public class RedisCacheService {

    @Autowired
    private RedisCacheRepository redisCacheRepository;

    public Mono<String> save(String key, String value) {

        try {
            
            return redisCacheRepository
                    .save(key, value)
                    .flatMap(saved -> { return Mono.just(value); });

        } catch (Exception e) {

            log.error("Erro ao tentar salvar cache para chave {}", key);
        }
        return Mono.just(value);
    }

    public Mono<String> get(String key) {
        try {

            return redisCacheRepository.get(key);

        } catch (Exception ex) {
            log.error("Erro ao tentar recuperar cache para chave {}", key);
        }

        return Mono.empty();
    }

    public Mono<Boolean> existForKey(String key) {

        try {

            return redisCacheRepository.existsForKey(key);

        } catch (Exception ex) {
            log.error("Erro ao tentar recuperar cache para chave {}", key, ex);
        }
        return Mono.just(false);
    }
}

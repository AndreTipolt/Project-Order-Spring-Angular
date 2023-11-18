package tipolt.andre.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import tipolt.andre.spring.repositories.RedisCacheRepository;

@Service
public class RedisCacheService {

    @Autowired
    private RedisCacheRepository redisCacheRepository;

    public Mono<Object> save(String key, Object object) {

        try {
            return redisCacheRepository.save(key, object)
                    .flatMap(saved -> { return Mono.just(saved); });
        } catch (Exception e) {
            System.out.println("Erro ao tentar salvar cache para chave {}");
        }
        return Mono.just(object);
    }

    public Mono<Object> get(Object key) {
        try {
            return redisCacheRepository.get(key);
        } catch (Exception ex) {
            System.out.println("Erro ao tentar recuperar cache para chave {}");
        }

        return Mono.empty();
    }

    public Mono<Boolean> existForKey(String key) {

        try {
            return redisCacheRepository.existsForKey(key);
        } catch (Exception ex) {
            System.out.println("Erro ao tentar recuperar cache para chave {}");
        }
        return Mono.just(false);
    }
}

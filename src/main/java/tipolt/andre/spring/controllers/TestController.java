package tipolt.andre.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tipolt.andre.spring.services.RedisCacheService;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    
    @Autowired
    private RedisCacheService redisCacheService;

    @GetMapping
    public Mono<String> save(){
        return redisCacheService.save("product", "valorzinho");
    }

    @GetMapping(value = "/{id}")
    public Mono<String> get(@PathVariable String id){
        return redisCacheService.get(id);
    }
}

package tipolt.andre.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tipolt.andre.spring.services.RedisCacheService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisCacheService redisCacheService;

    @PostMapping("/save}")
    public void saveJson(@RequestBody String key, @RequestBody String json) {
        redisCacheService.saveJson(key, json);
    }

    @GetMapping("/get/{key}")
    public String getJson(@PathVariable String key) {
        return redisCacheService.getJson(key);
    }
}
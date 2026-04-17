package com.Ganesh.SpringBoot_Tutorial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Ganesh, Spring Boot is running!";
    }

    @GetMapping("/bye")
    public String sayBye() {
        return "Goodbye!";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello " + name;
    }

    @GetMapping("/user")
    public Map<String, String> getUser() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "Ganesh");
        user.put("role", "Developer");
        return user;
    }
}

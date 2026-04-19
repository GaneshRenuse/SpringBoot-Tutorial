package com.Ganesh.SpringBoot_Tutorial;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HelloController {

    List<User> users = new ArrayList<>();

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

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return users;
    }
}

package com.sahith.broadcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BroadcastApplication {

    public static void main(String[] args) {
        SpringApplication.run(BroadcastApplication.class, args);
    }

    @GetMapping("/api/v1")
    public static String helloWorld() {
        return "Hello World!";
    }

}

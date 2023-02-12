package com.example.hyfit_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HyfitServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyfitServerApplication.class, args);
        System.out.println("hello");
    }

}

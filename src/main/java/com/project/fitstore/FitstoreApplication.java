package com.project.fitstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FitstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(FitstoreApplication.class, args);
    }
}

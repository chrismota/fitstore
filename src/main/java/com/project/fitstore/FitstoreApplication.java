package com.project.fitstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class FitstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitstoreApplication.class, args);
    }

}

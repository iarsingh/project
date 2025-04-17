package com.naukrisetu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NaukriSetuApplication {
    public static void main(String[] args) {
        SpringApplication.run(NaukriSetuApplication.class, args);
    }
} 
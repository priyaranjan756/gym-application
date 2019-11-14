package com.exercise.egym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.exercise.egym.model"})
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

}

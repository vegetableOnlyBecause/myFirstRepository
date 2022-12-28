package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.mapper")
public class BasicWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicWebApplication.class, args);
    }

}

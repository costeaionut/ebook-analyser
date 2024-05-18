package com.oni.ebookwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.oni"})
public class EbookWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbookWebappApplication.class, args);
    }

}

package com.wyx.toolbox.xss;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XssApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(XssApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("aaaaa");
    }
}

package com.wyx.toolbox.password;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangyu 
 */
@SpringBootApplication
public class PasswordCheckApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PasswordCheckApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {}
}

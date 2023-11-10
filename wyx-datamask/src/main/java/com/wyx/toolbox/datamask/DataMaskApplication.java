package com.wyx.toolbox.datamask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataMaskApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataMaskApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {}
}

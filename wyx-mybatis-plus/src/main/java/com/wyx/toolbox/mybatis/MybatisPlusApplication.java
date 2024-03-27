package com.wyx.toolbox.mybatis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangyu 
 */
@SpringBootApplication
public class MybatisPlusApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {}
}

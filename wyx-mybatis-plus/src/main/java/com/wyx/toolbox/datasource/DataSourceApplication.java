package com.wyx.toolbox.datasource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangyu 
 */
@SpringBootApplication(scanBasePackages = {"com.wyx.toolbox.datasource"})
public class DataSourceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataSourceApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {}
}

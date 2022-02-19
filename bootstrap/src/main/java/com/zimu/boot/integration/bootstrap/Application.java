package com.zimu.boot.integration.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zimu.boot.integration")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}

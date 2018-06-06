package com.taiying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableTransactionManagement
public class TaiyingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaiyingSystemApplication.class, args);
    }
}

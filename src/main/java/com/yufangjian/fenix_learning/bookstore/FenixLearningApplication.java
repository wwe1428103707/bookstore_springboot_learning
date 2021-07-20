package com.yufangjian.fenix_learning.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableCaching
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class FenixLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(FenixLearningApplication.class, args);
    }

}

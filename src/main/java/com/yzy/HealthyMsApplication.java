package com.yzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author LuBaby
 */
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class HealthyMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthyMsApplication.class, args);
    }

}

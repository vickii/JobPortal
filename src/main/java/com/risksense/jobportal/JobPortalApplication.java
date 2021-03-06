package com.risksense.jobportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
public class JobPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobPortalApplication.class, args);
    }
}

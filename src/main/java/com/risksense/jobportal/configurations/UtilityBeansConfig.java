package com.risksense.jobportal.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

@Configuration
public class UtilityBeansConfig {

    private static final int NO_OF_THREADS = 1;

    @Bean
    public Executor executorService(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(NO_OF_THREADS);
        return executor;
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

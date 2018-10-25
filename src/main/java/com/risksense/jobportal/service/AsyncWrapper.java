package com.risksense.jobportal.service;

import com.risksense.jobportal.entity.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsyncWrapper {

    public AsyncWrapper(@Autowired KafkaProducerService kafkaProducerService) {

        this.kafkaProducerService = kafkaProducerService;
    }

    private final KafkaProducerService kafkaProducerService;

    @Async
    public void readAndSendToKafka(List<Job> jobListToBeSaved)  {
        jobListToBeSaved.stream().map(e -> kafkaProducerService.sendToKafka(e)).collect(Collectors.toList());
        //can save the response and retry the failures if needed
    }

}

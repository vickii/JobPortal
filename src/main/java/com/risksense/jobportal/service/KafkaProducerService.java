package com.risksense.jobportal.service;

import com.risksense.jobportal.entity.Job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.risksense.jobportal.configurations.KafKaConstants.TOPIC;

@Service
public class KafkaProducerService {

    private final Logger LOG = LoggerFactory.getLogger(KafkaProducerService.class);


    public KafkaProducerService(@Autowired KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final KafkaTemplate<String, Job> kafkaTemplate;


    public Job sendToKafka(Job job) {
        kafkaTemplate.send(TOPIC, job).addCallback(
                new ListenableFutureCallback<SendResult<String, Job>>() {
                    @Override
                    public void onSuccess(SendResult<String, Job> result) {
                        LOG.info("Message sent successfully");
                    }
                    @Override
                    public void onFailure(Throwable ex) {
                        LOG.error("Sending failed");
                    }
                });
        return job;
    }
}

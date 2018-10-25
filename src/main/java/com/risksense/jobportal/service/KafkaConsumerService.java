package com.risksense.jobportal.service;

import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.exception.JobNotSaveableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import static com.risksense.jobportal.configurations.KafKaConstants.GROUPID;
import static com.risksense.jobportal.configurations.KafKaConstants.TOPIC;

@Service
public class KafkaConsumerService {

    public KafkaConsumerService(@Autowired JobService jobService) {
        this.jobService = jobService;
    }

    private final JobService jobService;

    @KafkaListener(topics = TOPIC, groupId = GROUPID, containerFactory = "kafkaListenerContainerFactory")
    public boolean consumeJobJson(@Valid Job job) {
        try {
            return jobService.createJob(job) != null;
        } catch (Exception ex) {
            throw new JobNotSaveableException("Not able to save the object to DB " + job);
        }
    }
}

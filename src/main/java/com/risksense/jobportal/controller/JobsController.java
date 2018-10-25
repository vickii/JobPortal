package com.risksense.jobportal.controller;

import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.exception.KafkaCommunicationException;
import com.risksense.jobportal.model.BulkUploadResponse;
import com.risksense.jobportal.model.SearchJobRequest;
import com.risksense.jobportal.service.JobService;
import com.risksense.jobportal.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.Valid;


@RestController
public class JobsController {

    private JobsController(@Autowired JobService jobService, @Autowired SearchService searchService) {
        this.jobService = jobService;
        this.searchService = searchService;
    }

    private final JobService jobService;

    private final SearchService searchService;


    @PostMapping(value = "/job", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Job> createJob(@Valid @RequestBody Job job) {
        return ResponseEntity.ok(jobService.createJob(job));
    }

    @PostMapping(value = "/bulkjob", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BulkUploadResponse> uploadFile(@RequestParam("file") MultipartFile file)  {
        return ResponseEntity.ok(jobService.bulkCreateJob(file));
    }

    @PostMapping(name = "/job/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Job>> searchJob(@RequestBody SearchJobRequest job) {
        return ResponseEntity.ok(searchService.searchJob(job));
    }
}

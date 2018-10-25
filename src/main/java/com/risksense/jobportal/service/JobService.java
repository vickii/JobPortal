package com.risksense.jobportal.service;

import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.exception.JobNotSaveableException;
import com.risksense.jobportal.exception.KafkaCommunicationException;
import com.risksense.jobportal.model.BulkUploadResponse;
import com.risksense.jobportal.utilities.RepoHelper;
import com.risksense.jobportal.utilities.Utilitites;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private JobService(@Autowired RepoHelper repoHelper, @Autowired AsyncWrapper asyncWrapper) {
        this.repoHelper = repoHelper;
        this.asyncWrapper = asyncWrapper;
    }

    private final RepoHelper repoHelper;

    private final AsyncWrapper asyncWrapper;

    public Job createJob(final Job job) {
        return repoHelper.save(job);
    }

    public BulkUploadResponse bulkCreateJob(final MultipartFile file)  {
        List<Job> JobsTobeAdded = null;
        try {
            JobsTobeAdded = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8")).lines().skip(1).filter(e -> e != null && !e.trim().isEmpty()).map(Utilitites::mapToJob).collect(Collectors.toList());
        } catch (IOException ex) {
            throw new JobNotSaveableException("Job is not saveable " + ExceptionUtils.getStackTrace(ex));
        }
        asyncWrapper.readAndSendToKafka(JobsTobeAdded);
        return new BulkUploadResponse(JobsTobeAdded != null ? JobsTobeAdded.size() : 0);
    }
}

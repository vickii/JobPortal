package com.risksense.jobportal.service;

import java.util.List;

import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.model.SearchJobRequest;

public interface SearchService {

    List<Job> searchJob(SearchJobRequest qry);
}

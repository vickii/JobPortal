package com.risksense.jobportal.utilities;

import com.risksense.jobportal.entity.Availability;
import com.risksense.jobportal.entity.Company;
import com.risksense.jobportal.entity.Experience;
import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.entity.JobType;
import com.risksense.jobportal.entity.Language;
import com.risksense.jobportal.entity.Skill;
import com.risksense.jobportal.model.SearchJobRequest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Utilitites {

    public static List<String> mapToSkill(SearchJobRequest qry) {
        if (qry.getSkill() == null) {
            return null;
        }
        return qry.getSkill().stream().map(Skill::getName).collect(
                Collectors.toList());
    }

    public static Job mapToJob(String jobString) {
        String[] jobFromString = jobString.split(",");
        return Stream.of(new Job())
                .map(job -> {
                    job.setTitle(jobFromString[0]);
                    job.setDescription(jobFromString[1]);
                    job.setCompany(Stream.of(new Company()).map(company -> {
                        company.setName(jobFromString[2]);
                        company.setCity(jobFromString[3]);
                        company.setState(jobFromString[4]);
                        company.setZipCode(jobFromString[5]);
                        company.setCountry(jobFromString[6]);
                        return company;
                    }).findFirst().get());
                    job.setSalary(Long.parseLong(jobFromString[7]));
                    job.setSkill(Arrays.stream(jobFromString[8].split(";")).map(e -> new Skill(e)).collect(Collectors.toSet()));
                    job.setExperience(Experience.valueOf(jobFromString[9]));
                    job.setAvailability(Availability.valueOf(jobFromString[10]));
                    job.setLanguage(Language.valueOf(jobFromString[11]));
                    job.setJobtype(JobType.valueOf(jobFromString[12]));
                    return job;
                }).findFirst().get();
    }
}

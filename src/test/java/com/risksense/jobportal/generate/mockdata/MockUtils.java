package com.risksense.jobportal.generate.mockdata;

import com.risksense.jobportal.entity.Availability;
import com.risksense.jobportal.entity.Company;
import com.risksense.jobportal.entity.Experience;
import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.entity.JobType;
import com.risksense.jobportal.entity.Language;
import com.risksense.jobportal.entity.Skill;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class MockUtils {

    public static Job generateMockJob() {
        Job job = new Job();
        job.setCompany(getCompany());
        job.setDescription("java developer");
        job.setExperience(Experience.FRESHER);
        job.setAvailability(Availability.FULL_TIME);
        job.setJobtype(JobType.DEVELOPER);
        job.setSalary(10000L);
        job.setLanguage(Language.ENGLISH);
        job.setTitle("JAVA");
        job.setSkill(getMockSkills(job));
        return job;
    }

    private static Set<Skill> getMockSkills(Job job) {
        Set<Skill> skillSet = new HashSet<>();
        skillSet.add(getMockSkill(job, "JAVA").setJob(new HashSet<>(Arrays.asList(job))));
        return skillSet;
    }

    private static Skill getMockSkill(Job job, String skills) {
        Skill skill = new Skill();
        skill.setName(skills);
        return skill;
    }

    private static Company getCompany() {
        Company company = new Company();
        company.setCity("Madurai");
        company.setCountry("INDIA");
        company.setName("DEMO COMPANY");
        company.setState("TN");
        company.setZipCode("123");
        return company;
    }
}

package com.risksense.jobportal.utilities;

import com.risksense.jobportal.entity.Company;
import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.entity.Skill;
import com.risksense.jobportal.repositories.CompanyRepository;
import com.risksense.jobportal.repositories.JobRepository;
import com.risksense.jobportal.repositories.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Component
public class RepoHelper {

    private final JobRepository jobRepository;

    private final CompanyRepository companyRepository;

    private final SkillRepository skillRepository;

    public RepoHelper(@Autowired JobRepository jobRepository, @Autowired CompanyRepository companyRepository, @Autowired SkillRepository skillRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.skillRepository = skillRepository;
    }

    @Transactional
    public Job save(final Job job) {
        Company companyFromDB = companyRepository.findFirstByNameAndCityAndStateAndZipCodeAndCountry(job.getCompany().getName(), job.getCompany().getCity(), job.getCompany().getState(), job.getCompany().getZipCode(), job.getCompany().getCountry());
        if (companyFromDB != null) {
            job.setCompany(companyFromDB);
        }
        final Set<Skill> skillSet = job.getSkill().stream()
                .map(skill -> {
                    Skill skillDB = skillRepository.findFirstByName(skill.getName());
                    if (skillDB != null) {
                        return skillDB;
                    } else {
                        return skill;
                    }
                })
                .collect(Collectors.toSet());
        job.setSkill(skillSet);
        return jobRepository.save(job);
    }

    public List<Job> findAllJobs(Specification<Job> specification) {
        return jobRepository.findAll(specification);
    }

    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    public Long jobCount() {
        return jobRepository.count();
    }

    public Long companyCount() {
        return companyRepository.count();
    }

    public Long skillCount() {
        return skillRepository.count();
    }

    public void deleteAllJobs() {
        jobRepository.deleteAll();
    }
}

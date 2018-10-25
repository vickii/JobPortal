package com.risksense.jobportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.risksense.jobportal.entity.Availability;
import com.risksense.jobportal.entity.Company;
import com.risksense.jobportal.entity.Experience;
import com.risksense.jobportal.entity.JobType;
import com.risksense.jobportal.entity.Language;
import com.risksense.jobportal.entity.Salary;
import com.risksense.jobportal.entity.Skill;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchJobRequest {

    private String title;

    private Company company;

    private Set<Skill> skill = new HashSet<>();

    private String description;

    private Experience experience;

    private Salary salaryBound;

    private Availability availability;

    private JobType jobtype;

    private Language language;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Skill> getSkill() {
        return skill;
    }

    public void setSkill(Set<Skill> skill) {
        this.skill = skill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public JobType getJobtype() {
        return jobtype;
    }

    public void setJobtype(JobType jobtype) {
        this.jobtype = jobtype;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Salary getSalaryBound() {
        return salaryBound;
    }

    public void setSalaryBound(Salary salaryBound) {
        this.salaryBound = salaryBound;
    }

    @Override
    public String toString() {
        return "SearchJobRequest{" +
                "title='" + title + '\'' +
                ", company=" + company +
                ", skill=" + skill +
                ", description='" + description + '\'' +
                ", experience=" + experience +
                ", salaryBound=" + salaryBound +
                ", availability=" + availability +
                ", jobtype=" + jobtype +
                ", language=" + language +
                '}';
    }
}

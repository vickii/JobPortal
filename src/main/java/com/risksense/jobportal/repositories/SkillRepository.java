package com.risksense.jobportal.repositories;

import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.entity.Skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SkillRepository extends JpaRepository<Skill, Long>, JpaSpecificationExecutor<Job> {

    Skill findFirstByName(String name);
}

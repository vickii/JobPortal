package com.risksense.jobportal.repositories;

import com.risksense.jobportal.entity.Company;
import com.risksense.jobportal.entity.Job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Job> {
    Company findFirstByNameAndCityAndStateAndZipCodeAndCountry(String name, String city, String state, String zipcode, String country);
}

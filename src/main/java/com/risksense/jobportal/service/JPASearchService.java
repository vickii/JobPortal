
package com.risksense.jobportal.service;

import com.risksense.jobportal.entity.Company;
import com.risksense.jobportal.entity.Company_;
import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.entity.Job_;
import com.risksense.jobportal.entity.Skill;
import com.risksense.jobportal.entity.Skill_;
import com.risksense.jobportal.model.SearchJobRequest;
import com.risksense.jobportal.utilities.RepoHelper;
import com.risksense.jobportal.utilities.Utilitites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Service
public class JPASearchService implements SearchService {

    public JPASearchService(@Autowired RepoHelper repoHelper) {

        this.repoHelper = repoHelper;

    }

    private final RepoHelper repoHelper;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Job> searchJob(SearchJobRequest qry) {
        return qry != null ? repoHelper.findAllJobs(new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                List<String> skills = Utilitites.mapToSkill(qry);
                if (!StringUtils.isEmpty(qry.getJobtype())) {
                    predicates.add(criteriaBuilder.equal(root.get(Job_.jobtype), qry.getJobtype()));
                }
                if (!StringUtils.isEmpty(qry.getAvailability())) {
                    predicates.add(criteriaBuilder.equal(root.get(Job_.availability), qry.getJobtype()));
                }
                if (!StringUtils.isEmpty(qry.getExperience())) {
                    predicates.add(criteriaBuilder.equal(root.get(Job_.experience), qry.getExperience()));
                }
                if (!StringUtils.isEmpty(qry.getTitle())) {
                    predicates.add(criteriaBuilder.equal(root.get(Job_.title), qry.getTitle()));
                }
                if (qry.getSalaryBound() != null) {
                    predicates.add(criteriaBuilder.between(root.get(Job_.salary).as(Long.class), qry.getSalaryBound().getSalaryFrom(), qry.getSalaryBound().getSalaryTo()));
                }
                if (qry.getLanguage() != null) {
                    predicates.add(criteriaBuilder.equal(root.get(Job_.language), qry.getLanguage()));
                }
                if (!CollectionUtils.isEmpty(skills)) {
                    Join<Job, Skill> join = root.join(Job_.skill);
                    Expression<String> parentExpression = join.get(Skill_.name);
                    predicates.add(parentExpression.in(skills));
                }
                if (qry.getCompany() != null) {
                    Company company = qry.getCompany();
                    Join<Job, Company> join = root.join(Job_.company);
                    if (!StringUtils.isEmpty(company.getCountry())) {
                        predicates.add(criteriaBuilder.equal(join.get(Company_.country), company.getCountry()));
                    }
                    if (!StringUtils.isEmpty(company.getName())) {
                        predicates.add(criteriaBuilder.equal(join.get(Company_.name), company.getName()));
                    }
                    if (!StringUtils.isEmpty(company.getCity())) {
                        predicates.add(criteriaBuilder.equal(join.get(Company_.city), company.getCity()));
                    }
                    if (!StringUtils.isEmpty(company.getState())) {
                        predicates.add(criteriaBuilder.equal(join.get(Company_.state), company.getState()));
                    }
                    if (!StringUtils.isEmpty(company.getZipCode())) {
                        predicates.add(criteriaBuilder.equal(join.get(Company_.zipCode), company.getZipCode()));
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }) : new ArrayList<Job>();
    }
}

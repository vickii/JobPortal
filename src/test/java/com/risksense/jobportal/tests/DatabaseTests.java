package com.risksense.jobportal.tests;

import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.generate.mockdata.MockUtils;
import com.risksense.jobportal.repositories.JobRepository;
import com.risksense.jobportal.utilities.RepoHelper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTests {

    /*We create a H2 database for these basic DB tests*/

    @Autowired
    private RepoHelper repoHelper;

    public static final long NO_OF_TESTDATA = 50;

    private static boolean INITIALIZED = false;


    @Before
    public void createTestData() {
        if (!INITIALIZED) {
            Stream.iterate(0, i -> i).limit(NO_OF_TESTDATA).map(e -> MockUtils.generateMockJob()).forEach(e -> repoHelper.save(e));
            INITIALIZED = true;
        }
    }

    @Test
    public void checkJobs() {
        Job job = repoHelper.findAllJobs().get(0);
        Assert.assertNotNull(job.getId());
        Assert.assertNotNull(job.getCompany().getId());
        Assert.assertNotNull(new ArrayList<>(job.getSkill()).get(0).getId());
        long jobsCount = repoHelper.jobCount();
        Assert.assertEquals(NO_OF_TESTDATA, jobsCount);
    }

    @Test
    public void checkCompany() {
        Job job = repoHelper.findAllJobs().get(0);
        Assert.assertNotNull(job.getId());
        Assert.assertNotNull(job.getCompany().getId());
        Assert.assertNotNull(new ArrayList<>(job.getSkill()).get(0).getId());
        long companyCount = repoHelper.companyCount();
        Assert.assertEquals(1, companyCount);
    }

    @Test
    public void checkSkills() {
        long skillCount = repoHelper.skillCount();
        Assert.assertEquals(1, skillCount);
    }

}

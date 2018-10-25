package com.risksense.jobportal.tests;

import com.risksense.jobportal.entity.Job;
import com.risksense.jobportal.generate.mockdata.MockUtils;
import com.risksense.jobportal.utilities.Utilitites;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BulkUploadTests {

    public static final String FILE_NAME = "BulkUploadTestFile.csv";

    private static File file;


    @Before
    public void createTestData() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        file = new File(classLoader.getResource(FILE_NAME).getFile());
        System.out.println(file.exists()+" "+file.getName());
    }

    @Test
    public void MapCSVFileToJob() throws FileNotFoundException {
        List<Job> JobsTobeAdded = new BufferedReader(new FileReader(file.getAbsolutePath())).lines().skip(1).filter(e -> e != null && !e.trim().isEmpty()).map(Utilitites::mapToJob).collect(Collectors.toList());
        Assert.assertEquals(JobsTobeAdded.size(), 50);
    }

}

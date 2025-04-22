package com.example.rndbatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobExecutionController {

    private final JobLauncher jobLauncher;
    private final Job myJobWithMultiStep;

    public JobExecutionController(JobLauncher jobLauncher, Job myJobWithMultiStep) {
        this.jobLauncher = jobLauncher;
        this.myJobWithMultiStep = myJobWithMultiStep;
    }

    @GetMapping("/execute-job")
    public String executeJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())  // You can customize params as needed
                    .toJobParameters();

            jobLauncher.run(myJobWithMultiStep, params);
            return "Job started successfully!";
        } catch (JobExecutionException e) {
            return "Job failed to start: " + e.getMessage();
        }
    }
}

package com.example.rndbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
//@ComponentScan(basePackages = {"com.example.rndbatch.*"})
public class RndBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(RndBatchApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner runJob(JobLauncher jobLauncher, Job myJobWithMultiStep) {
//        return args -> {
//            JobParameters params = new JobParametersBuilder()
//                    .addLong("time", System.currentTimeMillis())
//                    .toJobParameters();
//
//            jobLauncher.run(myJobWithMultiStep, params);
//        };
//    }
}

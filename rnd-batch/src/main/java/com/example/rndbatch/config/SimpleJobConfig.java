package com.example.rndbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class SimpleJobConfig {
//
//    @Bean
//    public Tasklet myTasklet() {
//        return (contribution, chunkContext) -> {
//            System.out.println("✅ Hello from Tasklet (no deprecated APIs)!");
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//
//    @Bean
//    public Step myStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, Tasklet myTasklet) {
//        return new StepBuilder("myStep", jobRepository)
//                .tasklet(myTasklet, transactionManager)
//                .build();
//    }
//
//
//    @Bean
//    public Job myJob(JobRepository jobRepository, Step myStep) {
//        return new JobBuilder("myJob", jobRepository)
//                .start(myStep)
//                .build();
//    }
//
//}

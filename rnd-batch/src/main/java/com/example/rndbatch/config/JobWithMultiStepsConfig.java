package com.example.rndbatch.config;

import com.example.rndbatch.service.ThirdTaskLet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobWithMultiStepsConfig {

@Autowired
private ThirdTaskLet thirdTaskLet;

    @Bean
    public Tasklet myFirstTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("✅ Hello from Tasklet 1 (First Step)!");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Tasklet mySecondTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("✅ Hello from Tasklet 2 (Second Step)!");
            return RepeatStatus.FINISHED;
        };
    }

//    @Bean
//    public Tasklet myThirdTasklet() {
//        return (contribution, chunkContext) -> {
//            System.out.println("✅ Hello from Tasklet 3 (Third Step)!");
//            return RepeatStatus.FINISHED;
//        };
//    }

    @Bean
    public Step myStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, Tasklet myFirstTasklet) {
        return new StepBuilder("myStep", jobRepository)
                .tasklet(myFirstTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step mySecondStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, Tasklet mySecondTasklet) {
        return new StepBuilder("mySecondStep", jobRepository)
                .tasklet(mySecondTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step myThirdStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("myThirdStep", jobRepository)
                .tasklet(thirdTaskLet, transactionManager)
                .build();
    }

    @Bean
    public Job myJob(JobRepository jobRepository, Step myStep, Step mySecondStep, Step myThirdStep) {
        return new JobBuilder("myJobWithMultiStep", jobRepository)
                .start(myStep)                // Start with the first step
                .next(mySecondStep)           // Proceed to the second step
                .next(myThirdStep)            // Then proceed to the third step
                .build();
    }

}

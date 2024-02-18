package com.danal.settlement.batch.job;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class PaymentJobConfig {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job paymentJob;

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void launchPaymentJob() throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(paymentJob, jobParameters);
    }
}

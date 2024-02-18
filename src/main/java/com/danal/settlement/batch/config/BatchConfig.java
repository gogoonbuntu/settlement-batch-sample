package com.danal.settlement.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<Payment> paymentReader() {
        // 결제 내역을 읽어오는 ItemReader 구현
        return new PaymentReader();
    }

    @Bean
    public ItemWriter<Payment> paymentWriter() {
        // 결제 내역을 파일로 쓰는 ItemWriter 구현
        return new PaymentWriter();
    }

    @Bean
    public Step paymentStep(ItemReader<Payment> reader, ItemWriter<Payment> writer) {
        return stepBuilderFactory.get("paymentStep")
                .<Payment, Payment>chunk(10)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job paymentJob(Step paymentStep) {
        return jobBuilderFactory.get("paymentJob")
                .incrementer(new RunIdIncrementer())
                .flow(paymentStep)
                .end()
                .build();
    }
}

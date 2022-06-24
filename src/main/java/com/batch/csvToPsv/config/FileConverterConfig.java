package com.batch.csvToPsv.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableBatchProcessing
@Profile("fileConverter")
@Slf4j
public class FileConverterConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ConverterReader converterReader;
    @Autowired
    private ConverterWriter converterWriter;
    /*
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ConverterReader converterReader;
    private final ConverterWriter converterWriter;

    public FileConverterConfig(JobBuilderFactory jobBuilderFactory,
                               StepBuilderFactory stepBuilderFactory,
                               ConverterReader converterReader,
                               ConverterWriter converterWriter) {
        this.jobBuilderFactory=jobBuilderFactory;
        this.stepBuilderFactory=stepBuilderFactory;
        this.converterReader=converterReader;
        this.converterWriter=converterWriter;
    }*/

    @Bean
    public Step reader() {
        return stepBuilderFactory
                .get("convertReader")
                .tasklet(converterReader)
                .build();
    }

    @Bean
    public Step writer() {
        return stepBuilderFactory
                .get("convertWriter")
                .tasklet(converterWriter)
                .build();
    }

    @Bean
    public Step converterStep(){

    }

    @Bean
    public Job converterJob() {
        return jobBuilderFactory
                .get("converterJob")
                .start(reader())
                .next(writer())
                .build();
    }

}

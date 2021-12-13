package com.batch.msbatchprocess.config;

import com.batch.msbatchprocess.exception.BatchException;
import com.batch.msbatchprocess.model.GameStats;
import com.batch.msbatchprocess.processor.GameStatsProcessor;
import com.batch.msbatchprocess.processor.JobCompletionNotificationListener;
import com.batch.msbatchprocess.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    GameRepository gameRepository;

    @Bean
    public FlatFileItemReader<GameStats> reader() {
        log.info("At reader");
        return new FlatFileItemReaderBuilder<GameStats>()
                .name("chessReader")
                .resource(new ClassPathResource("games.csv"))
                .delimited()
                .names(ApplicationConstants.ID,
                        ApplicationConstants.RATED,
                        ApplicationConstants.CREATEDAT,
                        ApplicationConstants.LATSMOVEAT,
                        ApplicationConstants.TURNS,
                        ApplicationConstants.VICTORY_STATUS,
                        ApplicationConstants.WINNER,
                        ApplicationConstants.INCREMENTMODE,
                        ApplicationConstants.WHITEID,
                        ApplicationConstants.WHITERATING,
                        ApplicationConstants.BLACKID,
                        ApplicationConstants.BLACKRATING,
                        ApplicationConstants.MOVES,
                        ApplicationConstants.OPENINGECO,
                        ApplicationConstants.OPENINGNAME,
                        ApplicationConstants.OPENINGPLY)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<GameStats>() {{
                    setTargetType(GameStats.class);
                }})
                .linesToSkip(1)
                .build();
    }

    @Bean
    public GameStatsProcessor processor() {
        return new GameStatsProcessor();
    }
    @Bean
    public RepositoryItemWriter<GameStats> gameWriter(DataSource dataSource) {
        log.info("Using repository Writer");
        return new RepositoryItemWriterBuilder<GameStats>()
                .repository(gameRepository)
                .methodName("save")
                .build();
    }


    @Bean
    public Job importJob(JobCompletionNotificationListener listener, Step step1) {
        log.info("At import job");
        return jobBuilderFactory.get("importJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }


    @Bean
    public Step step(RepositoryItemWriter<GameStats> writer) {
        log.info("At step");
        return stepBuilderFactory.get("step")
                .<GameStats, GameStats> chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .faultTolerant()
                .skipLimit(1)
                .skip(BatchException.class)
                .build();
    }
}

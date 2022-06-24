package com.batch.csvToPsv.config;

import com.opencsv.CSVReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.List;

@Slf4j
@Component
public class ConverterReader implements Tasklet, StepExecutionListener {
    @Value("${converter.file.input}")
    private String inputPath;

    private List<String[]> records;
    private FileReader reader;
    private CSVReader csvReader;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        this.reader = new FileReader(inputPath);
        this.csvReader = new CSVReader(reader);
        records = csvReader.readAll();
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Step initialised");
    }

    @SneakyThrows
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("After step");
       this.reader.close();
       this.csvReader.close();
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("records",this.records);

        return ExitStatus.COMPLETED;
    }
}

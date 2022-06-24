package com.batch.csvToPsv.config;

import com.opencsv.CSVWriter;
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

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Slf4j
@Component
public class ConverterWriter implements Tasklet, StepExecutionListener {
    private List<String[]> records;
    private File file;
    private FileWriter writer;
    private CSVWriter csvWriter;

    @Value("${converter.file.output}")
    private String outputPath;

    @Override
    @SneakyThrows
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        writer = new FileWriter(outputPath,true);
        csvWriter = new CSVWriter(writer,
                '|',CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        csvWriter.writeAll(records);
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.file = new File(outputPath);
        this.records = (List<String[]>) stepExecution.getJobExecution().getExecutionContext().get("records");
    }

    @Override
    @SneakyThrows
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Completed writer");
        csvWriter.close();
        writer.close();
        return ExitStatus.COMPLETED;
    }
}

package com.sb.php.step;

import com.sb.php.domain.CovidDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step stepJob(ItemReader<CovidDTO> itemReader, JdbcBatchItemWriter<CovidDTO> itemWriter) {
        log.info("StepConfig.stepJob, I=Begin, ");
        return stepBuilderFactory
                .get("DelimitedFileStep")
                .<CovidDTO, CovidDTO>chunk(1000)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }
}

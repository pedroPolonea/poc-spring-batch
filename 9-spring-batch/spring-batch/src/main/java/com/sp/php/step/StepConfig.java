package com.sp.php.step;

import com.sp.php.domain.CharactersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
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
    public Step stepJob(ItemReader<CharactersDTO> itemReader, ItemProcessor<CharactersDTO, CharactersDTO> itemProcessor, JdbcBatchItemWriter<CharactersDTO> itemWriter) {
        log.info("StepConfig.stepJob, I=Begin, ");
        return stepBuilderFactory
                .get("exProcessorStep")
                .<CharactersDTO, CharactersDTO>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
}

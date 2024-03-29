package com.sb.php.step;

import com.sb.php.domain.CovidDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.amqp.AmqpItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step stepJob(@Qualifier("delimitedFile") ItemReader<CovidDTO> itemReader, @Qualifier("amqpItemWriter") AmqpItemWriter<CovidDTO> itemWriter) {
        log.info("StepConfig.stepJob, I=Begin, ");
        return stepBuilderFactory
                .get("covid19Step")
                .<CovidDTO, CovidDTO>chunk(10)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }
}

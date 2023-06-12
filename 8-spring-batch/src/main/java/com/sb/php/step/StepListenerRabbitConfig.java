package com.sb.php.step;

import com.sb.php.domain.CovidDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.amqp.AmqpItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StepListenerRabbitConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step stepJobListenerRabbit(@Qualifier("rabbitListener") AmqpItemReader<CovidDTO> itemReader, @Qualifier("jdbcBatchItemWriter") JdbcBatchItemWriter<CovidDTO> itemWriter) {
        log.info("StepConfig.stepJobListenerRabbit, I=Begin, ");
        return stepBuilderFactory
                .get("covid19StepListenerRabbit")
                .<CovidDTO, CovidDTO>chunk(10)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }
}

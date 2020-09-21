package com.sb.php.writer;

import com.sb.php.config.RabbitConfig;
import com.sb.php.domain.CovidDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.amqp.AmqpItemWriter;
import org.springframework.batch.item.amqp.builder.AmqpItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AmqpItemWriterConfig {

    @Bean
    @StepScope
    public AmqpItemWriter<CovidDTO> amqpItemWriter(RabbitConfig rabbitConfig){
        log.info("AmqpItemWriterConfig.amqpItemWriter, I=Begin");

        return new AmqpItemWriterBuilder<CovidDTO>()
                .amqpTemplate(rabbitConfig.rabbitTemplate())
                .build();
    }
}

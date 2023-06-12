package com.sb.php.reader;

import com.sb.php.config.RabbitConfig;
import com.sb.php.domain.CovidDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.amqp.AmqpItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AmqpItemReaderReader {

    @Bean
    @StepScope
    public AmqpItemReader<CovidDTO> rabbitListener(RabbitConfig rabbitConfig){
        RabbitTemplate rabbitTemplate = rabbitConfig.rabbitTemplate();
        rabbitTemplate.setDefaultReceiveQueue(RabbitConfig.QueueEnum.BATCH_COVID.getQueue().getName());

        return new AmqpItemReader<CovidDTO>(rabbitTemplate);
    }
}

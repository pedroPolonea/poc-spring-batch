package com.sb.php.config;


import lombok.Getter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        createQueue();
        template.setExchange(QueueEnum.BATCH_COVID.getExchange().getName());
        template.setRoutingKey(QueueEnum.BATCH_COVID.getQueue().getName());
        template.setMessageConverter(new Jackson2JsonMessageConverter());

        return template;
    }

    @Bean
    public void createQueue() {
        AmqpAdmin amqpAdmin = amqpAdmin();
        RabbitTemplate template = new RabbitTemplate(connectionFactory());

        for (QueueEnum queueEnum : QueueEnum.values()) {
            queueEnum.getExchange().getType();
            amqpAdmin.declareExchange(queueEnum.getExchange());
            amqpAdmin.declareQueue(queueEnum.getQueue());
            amqpAdmin.declareBinding(createBinding(queueEnum));
        }
    }

    private Binding createBinding(final QueueEnum queueEnum){
        return new Binding(
                queueEnum.queue.getName(),
                Binding.DestinationType.QUEUE,
                queueEnum.exchange.getName(),
                queueEnum.queue.getName(),
                Map.of()
        );
    }


    @Getter
    enum QueueEnum {
        BATCH_COVID(new Queue("batch-covid-queue"), new DirectExchange("batch-covid-exchange"));

        private Queue queue;
        private Exchange exchange;
        QueueEnum(final Queue queue, final Exchange exchange){
            this.queue = queue;
            this.exchange = exchange;
        }
    }
}

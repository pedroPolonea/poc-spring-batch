package com.poc.sb.jobs.step;

import com.poc.sb.core.domain.entity.OrdersEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration()
public class ReceivedOrderConfigStep {

  //  @Autowired
//    private PlatformTransactionManager transactionManager;

    @Bean
    public Step receivedOrderStep(JobRepository jobRepository,
                                  PlatformTransactionManager transactionManager,
                                  ItemReader<OrdersEntity> reader,
                                  ItemProcessor<OrdersEntity, OrdersEntity> processor,
                                  ItemWriter<OrdersEntity> writer
                                  ) throws Exception {
        log.info("I=Iniciando step receivedOrder");
        return new StepBuilder("receivedOrderStep", jobRepository)
                .<OrdersEntity, OrdersEntity>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}

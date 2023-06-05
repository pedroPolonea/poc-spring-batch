package com.poc.sb.jobs.writer;

import com.poc.sb.core.domain.entity.OrdersEntity;
import com.poc.sb.core.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ReceivedOrderWriter implements ItemWriter<OrdersEntity> {

    @Autowired
    private OrderService orderService;

    @Override
    public void write(Chunk<? extends OrdersEntity> chunk) throws Exception {
        chunk.forEach(ordersEntity -> {
            orderService.save(ordersEntity);
        });
    }
}

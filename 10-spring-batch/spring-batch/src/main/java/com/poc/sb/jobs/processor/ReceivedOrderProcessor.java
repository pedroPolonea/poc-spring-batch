package com.poc.sb.jobs.processor;

import com.poc.sb.core.domain.entity.OrdersEntity;
import com.poc.sb.core.domain.enumerator.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ReceivedOrderProcessor implements ItemProcessor<OrdersEntity, OrdersEntity> {

    @Override
    public OrdersEntity process(final OrdersEntity order) throws Exception {
        log.info("I=Processando, order={}", order);
        order.setOrderStatus(OrderStatus.PAYMENT_ACCEPT);
        return order;
    }
}

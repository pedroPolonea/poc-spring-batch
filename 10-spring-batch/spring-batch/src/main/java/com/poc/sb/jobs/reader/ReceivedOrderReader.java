package com.poc.sb.jobs.reader;

import com.poc.sb.core.domain.entity.OrdersEntity;
import com.poc.sb.core.domain.enumerator.OrderStatus;
import com.poc.sb.core.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Objects;

@Slf4j
@Configuration
public class ReceivedOrderReader implements ItemReader<OrdersEntity> {

    @Autowired
    private OrderService orderService;

    Iterator<OrdersEntity> ordersIterable;

    @BeforeRead
    public void up() {
        ordersIterable = orderService.findByOrderStatusTop(OrderStatus.WE_RECEIVED_ORDER, 100)
                .iterator();
    }

    @Override
    public OrdersEntity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.info("I= Iniciando leitura ReceivedOrderReader, ordersIterable={}", ordersIterable);
        if (ordersIterable.hasNext()) {
            return ordersIterable.next();
        }

        return null;
    }
}

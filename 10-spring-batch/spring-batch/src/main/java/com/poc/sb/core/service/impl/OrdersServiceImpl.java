package com.poc.sb.core.service.impl;

import com.poc.sb.core.domain.entity.OrdersEntity;
import com.poc.sb.core.domain.enumerator.OrderStatus;
import com.poc.sb.core.repository.OrdersRepository;
import com.poc.sb.core.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class OrdersServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;


    @Override
    @Transactional(readOnly = true)
    public List<OrdersEntity> findByOrderStatusTop(final OrderStatus orderStatus, final int top) {
        log.info("I=Iniciando, orderStatus={}, top={}", orderStatus, top);

        return ordersRepository.findTop100ByOrderStatus(orderStatus);
    }

    @Override
    @Transactional
    public OrdersEntity save(OrdersEntity orders) {
        log.info("I=save, order={}", orders);
        return ordersRepository.save(orders);
    }
}

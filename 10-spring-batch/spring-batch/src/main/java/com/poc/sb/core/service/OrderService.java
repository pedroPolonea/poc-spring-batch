package com.poc.sb.core.service;

import com.poc.sb.core.domain.entity.OrdersEntity;
import com.poc.sb.core.domain.enumerator.OrderStatus;

import java.util.List;

public interface OrderService {

    List<OrdersEntity> findByOrderStatusTop(OrderStatus orderStatus, int top);

    OrdersEntity save(OrdersEntity orders);
}

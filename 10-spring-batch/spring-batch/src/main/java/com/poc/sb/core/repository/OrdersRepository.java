package com.poc.sb.core.repository;

import com.poc.sb.core.domain.entity.OrdersEntity;
import com.poc.sb.core.domain.enumerator.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    List<OrdersEntity> findTop100ByOrderStatus(OrderStatus status);
}

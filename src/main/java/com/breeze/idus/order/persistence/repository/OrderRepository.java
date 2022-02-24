package com.breeze.idus.order.persistence.repository;


import com.breeze.core.config.jpa.DefaultJpaRepository;
import com.breeze.idus.order.persistence.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends DefaultJpaRepository<OrderEntity, String> {

    List<OrderEntity> findByUserId(long userId);

    List<OrderEntity> findByIdIn(List<String> orderIds);
}

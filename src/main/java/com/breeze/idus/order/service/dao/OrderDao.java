package com.breeze.idus.order.service.dao;

import com.breeze.core.exception.EmptyDataException;
import com.breeze.idus.order.converter.OrderConverter;
import com.breeze.idus.order.persistence.entity.OrderEntity;
import com.breeze.idus.order.persistence.repository.OrderRepository;
import com.breeze.idus.order.service.vo.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderDao {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Transactional(readOnly = true)
    public List<Order> findByUserId(long userId) {
        List<OrderEntity> orderList = orderRepository.findByUserId(userId);

        return orderConverter.convertsList(orderList);
    }

    @Transactional(readOnly = true)
    public List<Order> findByOrderIds(List<String> orderIds) {
        if (orderIds.isEmpty()) {
            return new ArrayList<>();
        }

        List<OrderEntity> orderList = orderRepository.findByIdIn(orderIds);

        return orderConverter.convertsList(orderList);
    }
}

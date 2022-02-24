package com.breeze.idus.order.service;

import com.breeze.idus.order.service.dao.OrderDao;
import com.breeze.idus.order.service.vo.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    public List<Order> getUserOrders(long userId) {
        return orderDao.findByUserId(userId);
    }


}

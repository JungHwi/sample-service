package com.breeze.idus.user.service;

import com.breeze.core.auth.AuthToken;
import com.breeze.core.exception.BadRequestException;
import com.breeze.core.wrapper.JpaPageRequest;
import com.breeze.idus.order.service.dao.OrderDao;
import com.breeze.idus.order.service.vo.Order;
import com.breeze.idus.user.controller.model.request.UserRequest;
import com.breeze.idus.user.service.dao.UserDao;
import com.breeze.idus.user.service.vo.LastAction;
import com.breeze.idus.user.service.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final OrderDao orderDao;

    public User insert(User user) {
        return userDao.save(user);
    }

    public User getUser(long userId) {
        return userDao.findById(userId);
    }

    @Transactional(readOnly = true)
    public Page<User> searchUserList(UserRequest request, int limit, int offset) {
        Pageable pageable = JpaPageRequest.of(offset, limit);
        Page<User> users = userDao.searchUserList(request, pageable);

        List<String> orderIds = users.stream()
                .filter(user -> user.getLastAction() != null)
                .map(user -> user.getLastAction().getOrderId())
                .collect(Collectors.toList());

        List<Order> orderList = orderDao.findByOrderIds(orderIds);

        Map<String, Order> orderMap = orderList.stream()
                .collect(Collectors.toMap(Order::getId, Function.identity()));

        for (User user : users) {
            LastAction lastAction = user.getLastAction();
            if (lastAction != null) {
                Order lastOrder = orderMap.get(user.getLastAction().getOrderId());
                user.setLastOrder(lastOrder);
            }
        }

        return users;
    }
}

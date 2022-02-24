package com.breeze.idus.order.converter;

import com.breeze.idus.order.controller.model.OrderModel;
import com.breeze.idus.order.persistence.entity.OrderEntity;
import com.breeze.idus.order.service.vo.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConverter {

    OrderModel convertToModel(Order user);
    List<OrderModel> convertToModelList(List<Order> user);

    Order converts(OrderModel model);
    Order converts(OrderEntity entity);
    List<Order> convertsList(List<OrderEntity> entityList);

    OrderEntity convertToEntity(Order user);
}

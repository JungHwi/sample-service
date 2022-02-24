package com.breeze.idus.user.converter;

import com.breeze.idus.order.converter.OrderConverter;
import com.breeze.idus.user.controller.model.UserModel;
import com.breeze.idus.user.controller.model.UserOrderModel;
import com.breeze.idus.user.controller.model.request.UserRequest;
import com.breeze.idus.user.persistence.entity.UserEntity;
import com.breeze.idus.user.service.vo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LastActionConverter.class, OrderConverter.class})
public interface UserConverter {

    UserModel convertToModel(User user);

    @Mapping(target = "lastOrder", ignore = true)
    User converts(UserEntity entity);
    List<User> convertsList(List<UserEntity> entityList);

    @Mappings({
            @Mapping(target = "lastAction", ignore = true),
            @Mapping(target = "lastOrder", ignore = true)
    })
    User converts(UserRequest request);

    UserEntity convertToEntity(User user);

    List<UserOrderModel> convertUserOrderList(List<User> userList);

}

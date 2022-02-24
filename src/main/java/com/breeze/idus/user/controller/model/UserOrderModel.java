package com.breeze.idus.user.controller.model;

import com.breeze.idus.order.controller.model.OrderModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel("회원 & 마지막 주문 정보")
public class UserOrderModel {

    @ApiModelProperty("아이디")
    private Long id;

    @ApiModelProperty("이름")
    private String name;

    @ApiModelProperty("이메일")
    private String email;

    @ApiModelProperty("마지막 주문 정보")
    private OrderModel lastOrder;
}

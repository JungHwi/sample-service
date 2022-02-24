package com.breeze.idus.order.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.breeze.core.constants.DateConstants.LOCAL_DATE_TIME_FORMAT;

@Getter
@Builder
@ApiModel("주문 정보")
public class OrderModel {

    @ApiModelProperty("아이디")
    private String id;

    @ApiModelProperty("주문한 유저 아이디")
    private Long userId;

    @ApiModelProperty("상품명")
    private String productName;

    @ApiModelProperty("주문일시")
    @JsonFormat(pattern = LOCAL_DATE_TIME_FORMAT)
    private LocalDateTime createdAt;
    
}

package com.breeze.idus.order.service.vo;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class Order {

    private String id;

    private Long userId;

    private String productName;

    private LocalDateTime createdAt;
}

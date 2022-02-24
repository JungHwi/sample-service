package com.breeze.idus.user.service.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LastAction {
    private Long userId;

    private LocalDateTime loginAt;

    private String orderId;
}

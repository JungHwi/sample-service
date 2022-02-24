package com.breeze.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserActionType implements CodeInfo {
    LOGIN("로그인"),
    ORDER("주문");

    private final String description;
}

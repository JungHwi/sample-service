package com.breeze.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookStatus implements CodeInfo {
    AVAILABLE("정상"),
    UNAVAILABLE("비정상");

    private final String description;
}

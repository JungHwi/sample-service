package com.breeze.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender implements CodeInfo {
    F("여성"),
    M("남성");

    private final String description;
}

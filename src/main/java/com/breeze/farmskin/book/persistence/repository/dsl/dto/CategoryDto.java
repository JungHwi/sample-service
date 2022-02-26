package com.breeze.farmskin.book.persistence.repository.dsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CategoryDto {

    private long id;

    private String name;

    @QueryProjection
    public CategoryDto(long id, String name) {
        this.id = id;
        this.name = name;
    }
}

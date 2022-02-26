package com.breeze.farmskin.book.persistence.repository.dsl.dto;

import com.breeze.core.code.BookStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private long id;

    private String author;

    private String title;

    private BookStatus status;

    private List<CategoryDto> categoryList;

    @QueryProjection
    public BookDto(long id, String author, String title, BookStatus status, List<CategoryDto> categoryList) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.status = status;
        this.categoryList = categoryList;
    }
}

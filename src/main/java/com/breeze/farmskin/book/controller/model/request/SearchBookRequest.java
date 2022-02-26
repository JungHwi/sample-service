package com.breeze.farmskin.book.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SearchBookRequest {

    private Long categoryId;

    private String author;

    private String title;
}

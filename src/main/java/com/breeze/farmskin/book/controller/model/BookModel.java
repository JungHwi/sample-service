package com.breeze.farmskin.book.controller.model;

import com.breeze.core.code.BookStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("도서 정보")
public class BookModel {

    @ApiModelProperty("아이디")
    private Long id;

    @ApiModelProperty("지은이")
    private String author;

    @ApiModelProperty("제목")
    private String title;

    @ApiModelProperty("상태")
    private BookStatus status;

    @ApiModelProperty("카테고리 목록")
    private List<CategoryModel> categoryList;
}

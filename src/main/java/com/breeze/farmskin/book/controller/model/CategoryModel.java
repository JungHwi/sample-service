package com.breeze.farmskin.book.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel("카테고리 정보")
public class CategoryModel {

    @ApiModelProperty("아이디")
    private Long id;

    @ApiModelProperty("카테고리")
    private String name;
}

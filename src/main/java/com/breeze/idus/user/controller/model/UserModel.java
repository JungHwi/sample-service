package com.breeze.idus.user.controller.model;

import com.breeze.core.code.Gender;
import com.breeze.idus.order.service.vo.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel("회원 정보")
public class UserModel {
    @ApiModelProperty("아이디")
    private Long id;

    @ApiModelProperty("이름")
    private String name;

    @ApiModelProperty("닉네임")
    private String nickname;

    @ApiModelProperty("전화번호")
    private String phoneNumber;

    @ApiModelProperty("이메일")
    private String email;

    @ApiModelProperty("성별")
    private Gender gender;

}

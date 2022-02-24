package com.breeze.idus.user.controller.model.request;

import com.breeze.core.code.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("회원 요청 모델")
public class UserRequest {

    @ApiModelProperty("아이디")
    private Long id;

    @ApiModelProperty("이름")
    private String name;

    @ApiModelProperty("닉네임")
    private String nickname;

    @ApiModelProperty("암호")
    private String password;

    @ApiModelProperty("전화번호")
    private String phoneNumber;

    @ApiModelProperty("이메일")
    private String email;

    @ApiModelProperty("성별")
    private Gender gender;

}

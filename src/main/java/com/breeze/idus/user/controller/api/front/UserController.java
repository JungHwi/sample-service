package com.breeze.idus.user.controller.api.front;

import com.breeze.core.auth.AuthToken;
import com.breeze.core.wrapper.ResultResponse;
import com.breeze.idus.user.controller.model.UserModel;
import com.breeze.idus.user.controller.model.request.UserRequest;
import com.breeze.idus.user.converter.UserConverter;
import com.breeze.idus.user.service.UserService;
import com.breeze.idus.user.service.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User API")
@Log4j2
@RestController
@RequestMapping("/front/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @ApiOperation("회원 가입")
    @ApiResponses({
            @ApiResponse(code = 0, message = "요청 성공"),
            @ApiResponse(code = 40100, message = "잘못된 요청")
    })
    @PostMapping("")
    public ResultResponse<UserModel> insert(@RequestBody UserRequest userRequest) {
        User user = userConverter.converts(userRequest);
        User response = userService.insert(user);

        return new ResultResponse<>(userConverter.convertToModel(response));
    }

    @ApiOperation("단일 회원 상세 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 0, message = "요청 성공"),
            @ApiResponse(code = 40002, message = "잘못된 요청")
    })
    @GetMapping("/{userId}")
    public ResultResponse<UserModel> getUser(@PathVariable long userId) {

        User user = userService.getUser(userId);

        return new ResultResponse<>(userConverter.convertToModel(user));
    }
}

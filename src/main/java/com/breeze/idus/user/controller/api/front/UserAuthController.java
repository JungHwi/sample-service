package com.breeze.idus.user.controller.api.front;

import com.breeze.core.auth.AuthToken;
import com.breeze.core.exception.EmptyDataException;
import com.breeze.core.wrapper.EmptyResultModel;
import com.breeze.core.wrapper.ResultResponse;
import com.breeze.idus.user.controller.model.request.UserRequest;
import com.breeze.idus.user.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import static com.breeze.core.constants.SampleConstants.HEADER_USER_ID;

@Api(tags = "User Auth API")
@Log4j2
@RestController
@RequestMapping("/front/v1/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @ApiOperation("로그인 - email / password 필요")
    @ApiResponses({
            @ApiResponse(code = 0, message = "요청 성공"),
            @ApiResponse(code = 40002, message = "잘못된 요청")
    })
    @PostMapping("/login")
    public ResultResponse<AuthToken> login(@RequestBody UserRequest userRequest) {
        AuthToken token = userAuthService.login(userRequest.getEmail(), userRequest.getPassword());

        return new ResultResponse<>(token);
    }

    @ApiOperation("로그아웃")
    @ApiResponses({
            @ApiResponse(code = 0, message = "요청 성공"),
            @ApiResponse(code = 40002, message = "잘못된 요청")
    })
    @DeleteMapping("/logout")
    public ResultResponse<EmptyResultModel> logout(@RequestHeader(HEADER_USER_ID) long userId) {
        userAuthService.logout(userId);

        return new ResultResponse<>();
    }
}

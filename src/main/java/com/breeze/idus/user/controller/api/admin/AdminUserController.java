package com.breeze.idus.user.controller.api.admin;

import com.breeze.core.wrapper.PageContents;
import com.breeze.core.wrapper.ResultResponse;
import com.breeze.idus.order.service.vo.Order;
import com.breeze.idus.user.controller.model.UserModel;
import com.breeze.idus.user.controller.model.UserOrderModel;
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
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Admin User API")
@Log4j2
@RestController
@RequestMapping("/admin/v1/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @ApiOperation("회원 목록 조회")
    @ApiResponses({
            @ApiResponse(code = 0, message = "요청 성공"),
            @ApiResponse(code = 40002, message = "잘못된 요청")
    })
    @GetMapping("")
    public ResultResponse<PageContents<UserOrderModel>> searchUserList(
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(value = "offset", required = false, defaultValue = "1") int offset,
            UserRequest request) {

        Page<User> userList = userService.searchUserList(request, limit, offset);
        List<UserOrderModel> userOrderModel = userConverter.convertUserOrderList(userList.getContent());

        PageContents<UserOrderModel> result = new PageContents<>();
        result.setOffset(offset);
        result.setLimit(limit);
        result.setTotal((int)userList.getTotalElements());
        result.setContents(userOrderModel);

        return new ResultResponse<>(result);
    }
}

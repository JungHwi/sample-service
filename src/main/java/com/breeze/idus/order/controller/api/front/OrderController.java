package com.breeze.idus.order.controller.api.front;

import com.breeze.core.wrapper.ResultResponse;
import com.breeze.idus.order.controller.model.OrderModel;
import com.breeze.idus.order.converter.OrderConverter;
import com.breeze.idus.order.service.OrderService;
import com.breeze.idus.order.service.vo.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.breeze.core.constants.SampleConstants.HEADER_USER_ID;

@Api(tags = "Order API")
@Log4j2
@RestController
@RequestMapping("/front/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @ApiOperation("회원 주문 목록 조회")
    @ApiResponses({
            @ApiResponse(code = 0, message = "요청 성공"),
            @ApiResponse(code = 40002, message = "잘못된 요청")
    })
    @GetMapping("")
    public ResultResponse<List<OrderModel>> getUserOrders(@RequestHeader(HEADER_USER_ID) long userId) {

        List<Order> orderList = orderService.getUserOrders(userId);

        return new ResultResponse<>(orderConverter.convertToModelList(orderList));
    }
}

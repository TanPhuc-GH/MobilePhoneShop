package com.hcmute.MobilePhoneShop.controllers;

import com.hcmute.MobilePhoneShop.dtos.request.RequestCheckoutDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.services.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Base64;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    private ResponseEntity<BaseResponse> checkout (@Valid @RequestBody RequestCheckoutDTO requestCheckoutDTO){
        try {
            return ResponseEntity.ok(orderService.checkout(requestCheckoutDTO));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse(false,e.getMessage()));
        }
    }
}

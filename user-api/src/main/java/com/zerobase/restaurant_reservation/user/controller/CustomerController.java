package com.zerobase.restaurant_reservation.user.controller;

import com.zerobase.restaurant_reservation.user.domain.customer.CustomerDto;
import com.zerobase.restaurant_reservation.user.domain.model.Customer;
import com.zerobase.restaurant_reservation.user.exception.CustomException;
import com.zerobase.restaurant_reservation.user.exception.ErrorCode;
import com.zerobase.restaurant_reservation.user.service.customer.CustomerService;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.domain.domain.common.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;

    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token){
        UserVo vo = provider.getUserVo(token);
        Customer c = customerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER));
        return ResponseEntity.ok(CustomerDto.from(c));
    }

}

package com.zerobase.restaurant_reservation.user.application;

import com.zerobase.restaurant_reservation.user.domain.SignInForm;
import com.zerobase.restaurant_reservation.user.domain.model.Customer;
import com.zerobase.restaurant_reservation.user.domain.model.Manager;
import com.zerobase.restaurant_reservation.user.exception.CustomException;
import com.zerobase.restaurant_reservation.user.exception.ErrorCode;
import com.zerobase.restaurant_reservation.user.service.customer.CustomerService;
import com.zerobase.restaurant_reservation.user.service.manager.ManagerService;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.domain.domain.common.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final ManagerService managerService;
    private final JwtAuthenticationProvider provider;

    public String customerLoginToken(SignInForm form){
        // 1. 로그인 가능 여부
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        // 2. 토큰을 발행하고

        // 3. 토큰을 response 한다.
        return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
    }

    public String managerLoginToken(SignInForm form){
        // 1. 로그인 가능 여부
        Manager s = managerService.findValidManager(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        // 2. 토큰을 발행하고

        // 3. 토큰을 response 한다.
        return provider.createToken(s.getEmail(), s.getId(), UserType.SELLER);
    }
}

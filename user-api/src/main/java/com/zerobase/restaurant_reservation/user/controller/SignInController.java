package com.zerobase.restaurant_reservation.user.controller;

import com.zerobase.restaurant_reservation.user.application.SignInApplication;
import com.zerobase.restaurant_reservation.user.domain.SignInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/signin")
@RequiredArgsConstructor
public class SignInController {

    private final SignInApplication signInApplication;

    @PostMapping("/customer")
    public ResponseEntity<String> signInCustomer(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.customerLoginToken(form));
    }

    @PostMapping("/manager")
    public ResponseEntity<String> signInManager(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.managerLoginToken(form));
    }


}

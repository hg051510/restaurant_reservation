package com.zerobase.restarurant_reservation.restaurant.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ALREADY_REGISTERED_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    NEED_PARTNER_REGISTER(HttpStatus.BAD_REQUEST, "파트너 가입이 필요합니다.")
    ;
    private final HttpStatus httpStatus;
    private final String detail;

}

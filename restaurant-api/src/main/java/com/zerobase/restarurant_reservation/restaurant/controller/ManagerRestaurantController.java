package com.zerobase.restarurant_reservation.restaurant.controller;

import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.restarurant_reservation.restaurant.domain.RegisterRestaurantForm;
import com.zerobase.restarurant_reservation.restaurant.domain.restaurant.RestaurantDto;
import com.zerobase.restarurant_reservation.restaurant.service.RegisterRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/register")
@RequiredArgsConstructor
public class ManagerRestaurantController {
    private final JwtAuthenticationProvider provider;
    private final RegisterRestaurantService registerRestaurantService;

    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantDto> restaurantRegister(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                            @RequestBody RegisterRestaurantForm form){

        return ResponseEntity.ok(RestaurantDto.from(
                registerRestaurantService.registerRestaurant(provider.getUserVo(token).getId(), form)));
    }
}

package com.zerobase.restarurant_reservation.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRestaurantForm {
    private String name;
    private String address;
    private String phone;
    private String description;
}

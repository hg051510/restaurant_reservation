package com.zerobase.restarurant_reservation.restaurant.domain.restaurant;

import com.zerobase.restarurant_reservation.restaurant.domain.model.Restaurant;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String description;

    public static RestaurantDto from(Restaurant restaurant){
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phone(restaurant.getPhone())
                .description(restaurant.getDescription())
                .build();
    }
}

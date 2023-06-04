package com.zerobase.restarurant_reservation.restaurant.service;

import com.zerobase.restarurant_reservation.restaurant.domain.RegisterRestaurantForm;
import com.zerobase.restarurant_reservation.restaurant.domain.model.Restaurant;
import com.zerobase.restarurant_reservation.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterRestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant registerRestaurant(Long managerId, RegisterRestaurantForm form){
        return restaurantRepository.save(Restaurant.of(managerId, form));
    }
}

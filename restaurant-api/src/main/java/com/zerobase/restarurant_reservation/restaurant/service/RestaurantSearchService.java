package com.zerobase.restarurant_reservation.restaurant.service;

import com.zerobase.restarurant_reservation.restaurant.domain.model.Restaurant;
import com.zerobase.restarurant_reservation.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantSearchService {
    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> searchRestaurantByName(String name){
        return restaurantRepository.searchByName(name);
    }
}

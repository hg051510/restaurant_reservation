package com.zerobase.restarurant_reservation.restaurant.domain.repository;

import com.zerobase.restarurant_reservation.restaurant.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> searchByName(String name);

}

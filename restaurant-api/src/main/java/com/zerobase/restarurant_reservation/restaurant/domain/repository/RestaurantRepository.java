package com.zerobase.restarurant_reservation.restaurant.domain.repository;

import com.zerobase.restarurant_reservation.restaurant.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}

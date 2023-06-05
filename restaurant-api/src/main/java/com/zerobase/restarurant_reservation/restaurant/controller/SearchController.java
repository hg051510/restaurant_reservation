package com.zerobase.restarurant_reservation.restaurant.controller;

import com.zerobase.restarurant_reservation.restaurant.domain.restaurant.RestaurantDto;
import com.zerobase.restarurant_reservation.restaurant.service.RestaurantSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search/restaurant")
@RequiredArgsConstructor
public class SearchController {
    private final RestaurantSearchService restaurantSearchService;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> searchRestaurantByName(@RequestParam String name){
        return ResponseEntity.ok(restaurantSearchService.searchRestaurantByName(name).stream()
                .map(RestaurantDto::from).collect(Collectors.toList()));
    }

    // todo : 예약 테이블 생성, 예약 서비스, 예약 확인, 리뷰
}

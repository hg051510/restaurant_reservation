package com.zerobase.restaurant_reservation.user.domain.customer;

import com.zerobase.restaurant_reservation.user.domain.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String email;

    public static CustomerDto from(Customer customer){
        return new CustomerDto(customer.getId(), customer.getEmail());
    }
}

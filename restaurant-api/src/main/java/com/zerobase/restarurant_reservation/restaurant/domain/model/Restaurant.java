package com.zerobase.restarurant_reservation.restaurant.domain.model;

import com.zerobase.restarurant_reservation.restaurant.domain.RegisterRestaurantForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class Restaurant extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long managerId;

    private String name;

    private String address;

    private String phone;

    private String description;

    public static Restaurant of(Long managerId, RegisterRestaurantForm form){
        return Restaurant.builder()
                .managerId(managerId)
                .name(form.getName())
                .address(form.getAddress())
                .phone(form.getPhone())
                .description(form.getDescription())
                .build();
    }
}

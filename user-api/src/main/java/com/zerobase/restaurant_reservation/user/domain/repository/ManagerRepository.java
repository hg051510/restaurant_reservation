package com.zerobase.restaurant_reservation.user.domain.repository;

import com.zerobase.restaurant_reservation.user.domain.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByIdAndEmail(Long id, String email);

    Optional<Manager> findByEmailAndPassword(String email, String password);

    Optional<Manager> findByEmailAndPasswordAndVerifyIsTrue(String email, String password);

    Optional<Manager> findByEmailAndPasswordAndPartnerVerifyIsTrue(String email, String password);

    Optional<Manager> findByIdAndPartnerVerifyIsTrue(Long id);

    Optional<Manager> findByEmail(String email);
}

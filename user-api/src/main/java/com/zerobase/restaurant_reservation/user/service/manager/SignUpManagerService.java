package com.zerobase.restaurant_reservation.user.service.manager;

import com.zerobase.restaurant_reservation.user.domain.SignInForm;
import com.zerobase.restaurant_reservation.user.domain.SignUpForm;
import com.zerobase.restaurant_reservation.user.domain.model.Manager;
import com.zerobase.restaurant_reservation.user.domain.repository.ManagerRepository;
import com.zerobase.restaurant_reservation.user.exception.CustomException;
import com.zerobase.restaurant_reservation.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpManagerService {
    private final ManagerRepository managerRepository;

    public Manager signUp(SignUpForm form) {
        return managerRepository.save(Manager.from(form));
    }

    public boolean isEmailExist(String email) {
        return managerRepository.findByEmail(email).isPresent();
    }

    public boolean isPartner(String email, String password){
        return managerRepository.findByEmailAndPasswordAndPartnerVerifyIsTrue(email, password).isPresent();
    }

    @Transactional
    public void changeManagerPartnerVerify(SignInForm form){
        Optional<Manager> managerOptional =
                managerRepository.findByEmailAndPassword(form.getEmail(), form.getPassword());

        if(managerOptional.isPresent()){
            Manager manager = managerOptional.get();
            manager.setPartnerVerify(true);
        }else{
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }
    }

    @Transactional
    public void verifyEmail(String email, String code) {
        Manager manager = managerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        if (manager.isVerify()) {
            throw new CustomException(ErrorCode.ALREADY_VERIFY);
        } else if (!manager.getVerificationCode().equals(code)) {
            throw new CustomException(ErrorCode.WRONG_VERIFICATION);
        } else if (manager.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.EXPIRE_CODE);
        }
        manager.setVerify(true);
    }

    @Transactional
    public LocalDateTime changeManagerValidateEmail(Long managerId, String verificationCode) {
        Optional<Manager> managerOptional = managerRepository.findById(managerId);

        if (managerOptional.isPresent()) {
            Manager manager = managerOptional.get();
            manager.setVerificationCode(verificationCode);
            manager.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));

            return manager.getVerifyExpiredAt();
        }
        throw new CustomException(ErrorCode.NOT_FOUND_USER);
    }

}

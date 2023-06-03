package com.zerobase.restaurant_reservation.user.application;

import com.zerobase.restaurant_reservation.user.client.MailgunClient;
import com.zerobase.restaurant_reservation.user.client.mailgun.SendMailForm;
import com.zerobase.restaurant_reservation.user.domain.SignUpForm;
import com.zerobase.restaurant_reservation.user.domain.model.Customer;
import com.zerobase.restaurant_reservation.user.domain.model.Manager;
import com.zerobase.restaurant_reservation.user.exception.CustomException;
import com.zerobase.restaurant_reservation.user.exception.ErrorCode;
import com.zerobase.restaurant_reservation.user.service.customer.SignUpCustomerService;
import com.zerobase.restaurant_reservation.user.service.manager.SignUpManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpApplication {
    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;
    private final SignUpManagerService signUpManagerService;

    /* --------------- Customer --------------- */

    public void customerVerify(String email, String code) {
        signUpCustomerService.verifyEmail(email, code);
    }

    public String customerSignUp(SignUpForm form) {
        if (signUpCustomerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
            Customer c = signUpCustomerService.signUp(form);

            String code = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("test@zerobase.com")
                    .to(form.getEmail())
                    .subject("Verification Email!")
                    .text(getVerificationEmailBody(c.getEmail(), c.getName(), "customer", code))
                    .build();

            log.info("Send email result : " + mailgunClient.sendEmail(sendMailForm).getBody());
            signUpCustomerService.changeCustomerValidateEmail(c.getId(), code);
            return "회원 가입에 성공하셨습니다.";
        }
    }

    /* --------------- Manager --------------- */

    public void managerVerify(String email, String code) {
        signUpManagerService.verifyEmail(email, code);
    }

    public String managerSignUp(SignUpForm form) {
        if (signUpManagerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
            Manager s = signUpManagerService.signUp(form);

            String code = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("test@zerobase.com")
                    .to(form.getEmail())
                    .subject("Verification Email!")
                    .text(getVerificationEmailBody(s.getEmail(), s.getName(), "manager", code))
                    .build();
            log.info("Send email result : " + mailgunClient.sendEmail(sendMailForm).getBody());
            signUpManagerService.changeManagerValidateEmail(s.getId(), code);
            return "회원 가입에 성공하셨습니다.";
        }
    }



    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String type, String code) {
        StringBuilder sb = new StringBuilder();
        return sb.append("Hello").append(name).append("! Please Click Link for verification. \n\n")
                .append("http://localhost:8081/signup/"+type+"/verify/?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();

    }
}

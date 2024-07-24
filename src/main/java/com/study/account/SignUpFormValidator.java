package com.study.account;

import com.study.form.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {


    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SignUpForm signUpFormDto = (SignUpForm)target;

       if(accountRepository.existsByEmail(signUpFormDto.getEmail()))
           errors.rejectValue("email", "invalid.email",
                   new Object[]{signUpFormDto.getEmail()}, "이미 사용중인 이메일입니다.");


       if(accountRepository.existsByNickname(signUpFormDto.getNickname()))
           errors.rejectValue("nickname","invalid.nickname",
                   new Object[]{signUpFormDto.getNickname()}, "이미 사용중인 닉네임입니다.");
        // TODO email, nickname

    }
}

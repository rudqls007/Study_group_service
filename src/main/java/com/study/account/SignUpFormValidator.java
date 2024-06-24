package com.study.account;

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
        return clazz.isAssignableFrom(SignUpFormDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SignUpFormDto signUpFormDto = (SignUpFormDto)errors;

       if(accountRepository.existByEmail(signUpFormDto.getEmail()))
           errors.rejectValue("email", "invalid.email",
                   new Object[]{signUpFormDto.getEmail()}, "이미 사용중인 이메일입니다.");


       if(accountRepository.existByNickname(signUpFormDto.getNickname()))
           errors.rejectValue("nickname","invalid.nickname",
                   new Object[]{signUpFormDto.getNickname()}, "이미 사용중인 닉네임입니다.");
        // TODO email, nickname

    }
}

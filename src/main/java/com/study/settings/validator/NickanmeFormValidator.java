package com.study.settings.validator;

import com.study.account.AccountRepository;
import com.study.domain.Account;
import com.study.settings.form.NicknameForm;
import com.study.settings.form.PasswordForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class NickanmeFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NicknameForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NicknameForm nicknameForm = (NicknameForm) target;
        Account byNickname = accountRepository.findByNickname(nicknameForm.getNickname());
        if (byNickname != null) {
            errors.rejectValue("nickname",
                    "wrong.value", "입력한 닉네임을 사용할 수 없습니다.");
        }
    }
}

package com.study.account;

import com.study.domain.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @MockBean
    JavaMailSender javaMailSender;


    @Autowired private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;


    @Test
    @DisplayName("인증 메일 확인 - 입력 값 오류")
    void checkEmailToken_with_wrong_input() throws Exception {

        mockMvc.perform(get("/check-email-token")
                        .param("token", "sdfjqweq")
                        .param("email", "email@naver.com"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("account/checked-email"))
                .andExpect(unauthenticated());
    }


    @Test
    @DisplayName("인증 메일 확인 - 입력 값 정상")
    void checkEmailToken() throws Exception {
        Account account = Account.builder()
                .email("test@email.com")
                .password("12345678")
                .nickname("kyungbin")
                .build();
        Account newAccount = accountRepository.save(account);
        newAccount.generateEmailCheckToken();

        mockMvc.perform(get("/check-email-token")
                        .param("token", newAccount.getEmailCheckToken())
                        .param("email", newAccount.getEmail()))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("error"))
                .andExpect(model().attributeExists("nickname"))
                .andExpect(model().attributeExists("numberOfUser"))
                .andExpect(view().name("account/checked-email"))
                .andExpect(authenticated().withUsername("kyungbin"));

    }



    @Test
    @DisplayName("회원 가입 화면 보이는지 테스트")
    void SignupForm() throws Exception {

        mockMvc.perform(get("/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/sign-up"))
                .andExpect(model().attributeExists("signUpForm"))
                .andExpect(unauthenticated());

    }

    @DisplayName("회원 가입 처리 - 입력값 오류")
    @Test
    void signUpSubmit_with_wrong_input() throws Exception {
        mockMvc.perform(post("/sign-up")
                        .param("nickname", "keesun")
                        .param("email", "email..")
                        .param("password", "12345")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("account/sign-up"))
                .andExpect(unauthenticated());
    }



    @Test
    @DisplayName("회원 가입 처리 - 입력값 정상")
    void signUpSubmit_with_correct_input() throws Exception {
        mockMvc.perform(post("/sign-up")
                        .param("nickname", "kyungbin")
                        .param("email", "email@naver.com")
                        .param("password", "qwer1234")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(authenticated().withUsername("kyungbin"));

        Account account = accountRepository.findByEmail("email@naver.com");

        assertNotNull(account);
        assertNotEquals(account.getPassword(), "qwer1234");
        assertNotNull(account.getEmailCheckToken());
        System.out.println("account.getPassword() = " + account.getPassword());
        assertTrue(accountRepository.existsByEmail("email@naver.com"));
        then(javaMailSender).should().send(any(SimpleMailMessage.class));
    }


}
package com.study.main;

import com.study.account.AccountRepository;
import com.study.account.AccountService;
import com.study.account.form.SignUpForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void signupForm() {
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail("test@email.com");
        signUpForm.setNickname("kyungbin");
        signUpForm.setPassword("12345678");
        accountService.processNewAccount(signUpForm);
    }

    @AfterEach
    void afterEach() {
        accountRepository.deleteAll();
    }


    @Test
    @DisplayName("이메일로 로그인 성공")
    void login_with_email() throws Exception{

        mockMvc.perform(post("/login")
                        .param("username", "test@email.com")
                        .param("password", "12345678")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("kyungbin"));

    }


    @Test
    @DisplayName("닉네임으로 로그인 성공")
    void login_with_nickname() throws Exception{

        mockMvc.perform(post("/login")
                        .param("username", "kyungbin")
                        .param("password", "12345678")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("kyungbin"));

    }



    @Test
    @DisplayName("로그인 실패")
    void login_fail() throws Exception{

        mockMvc.perform(post("/login")
                        .param("username", "1111111")
                        .param("password", "11111")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(unauthenticated());

    }


    @Test
    @DisplayName("로그아웃")
    void logout() throws Exception{

        mockMvc.perform(post("/logout")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(unauthenticated());

    }



}
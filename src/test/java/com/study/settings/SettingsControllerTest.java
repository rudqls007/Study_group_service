package com.study.settings;

import com.study.WithAccount;
import com.study.account.AccountRepository;
import com.study.domain.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class SettingsControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @AfterEach
    void afterEach() throws Exception {
        accountRepository.deleteAll();
    }


    @WithAccount("kyungbin")
    @DisplayName("프로필 수정 폼")
    @Test
    void updateProfileForm() throws Exception {
        mockMvc.perform(get(SettingsController.SETTINGS_PROFILE_URL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"));

    }

    @WithAccount("kyungbin")
    @DisplayName("프로필 수정하기 - 입력값 정상")
    @Test
    void updateProfile() throws Exception {


        String bio = "짧은 소개를 수정하는 경우.";

        mockMvc.perform(post(SettingsController.SETTINGS_PROFILE_URL)
                        .param("bio", bio)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(SettingsController.SETTINGS_PROFILE_URL))
                .andExpect(flash().attributeExists("message"));

        Account kyungbin = accountRepository.findByNickname("kyungbin");
        assertEquals(bio, kyungbin.getBio());


    }


    @WithAccount("kyungbin")
    @DisplayName("프로필 수정하기 - 입력값 오류")
    @Test
    void updateProfile_fail() throws Exception {


        String bio = "짧은 소개를 수정하는 경우. 너무 많이 수정 너무 많이 수정 너무 많이 수정 너무 많이 수정 너무 많이 수정 너무 많이 수정";

        mockMvc.perform(post(SettingsController.SETTINGS_PROFILE_URL)
                        .param("bio", bio)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(SettingsController.SETTINGS_PROFILE_VIEW_NAME))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"))
                .andExpect(model().hasErrors());

        Account kyungbin = accountRepository.findByNickname("kyungbin");
        assertNull(kyungbin.getBio());


    }

    @WithAccount("kyungbin")
    @Test
    @DisplayName("패스워드 폼")
    void PasswordForm() throws Exception {
        mockMvc.perform(get(SettingsController.SETTINGS_PASSWORD_URL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("passwordForm"));
    }


    @WithAccount("kyungbin")
    @Test
    @DisplayName("패스워드 수정 - 입력값 정상")
    void PasswordUpdateForm() throws Exception {
        mockMvc.perform(post(SettingsController.SETTINGS_PASSWORD_URL)
                        .with(csrf())
                        .param("newPassword", "12341234")
                        .param("newPasswordConfirm", "12341234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(SettingsController.SETTINGS_PASSWORD_URL))
                .andExpect(flash().attributeExists("message"));

        Account kyungbin = accountRepository.findByNickname("kyungbin");
        assertTrue(passwordEncoder.matches("12341234", kyungbin.getPassword()));

    }

    @WithAccount("kyungbin")
    @Test
    @DisplayName("패스워드 수정 - 입력값 오류")
    void PasswordUpdateForm_fail() throws Exception {
        mockMvc.perform(post(SettingsController.SETTINGS_PASSWORD_URL)
                        .with(csrf())
                        .param("newPassword", "12341234")
                        .param("newPasswordConfirm", "1111111"))
                .andExpect(status().isOk())
                .andExpect(view().name(SettingsController.SETTINGS_PASSWORD_VIEW_NAME))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("passwordForm"));

    }




}
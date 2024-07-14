package com.study.settings;

import com.study.WithAccount;
import com.study.account.AccountRepository;
import com.study.account.AccountService;
import com.study.account.SignUpForm;
import com.study.domain.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

}
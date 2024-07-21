package com.study.settings;

import com.study.account.AccountService;
import com.study.account.CurrentUser;
import com.study.domain.Account;
import com.study.settings.form.NicknameForm;
import com.study.settings.form.Notifications;
import com.study.settings.form.PasswordForm;
import com.study.settings.form.Profile;
import com.study.settings.validator.NickanmeFormValidator;
import com.study.settings.validator.PasswordFormValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SettingsController {

    static final String SETTINGS_PROFILE_VIEW_NAME = "settings/profile";
    static final String SETTINGS_PROFILE_URL = "/settings/profile";

    static final String SETTINGS_PASSWORD_VIEW_NAME = "settings/password";
    static final String SETTINGS_PASSWORD_URL = "/settings/password";

    static final String SETTINGS_NOTIFICATIONS_VIEW_NAME = "settings/notifications";
    static final String SETTINGS_NOTIFICATIONS_URL = "/settings/notifications";

    static final String SETTINGS_NICKNAME_VIEW_NAME = "settings/account";
    static final String SETTINGS_NICKNAME_URL = "/settings/account";

    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final NickanmeFormValidator nickanmeFormValidator;


    @InitBinder("passwordForm")
    public void initPasswordBinder(WebDataBinder binder) {
        binder.addValidators(new PasswordFormValidator());
    }

    @InitBinder("nicknameForm")
    public void initNicknameBinder(WebDataBinder binder) {
        binder.addValidators(nickanmeFormValidator);
    }


    @GetMapping(SETTINGS_PROFILE_URL)
    public String updateProfileForm(@CurrentUser Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(modelMapper.map(account, Profile.class));

        return SETTINGS_PROFILE_VIEW_NAME;
    }

    @PostMapping("/settings/profile")
    public String updateProfile(@CurrentUser Account account, @Valid Profile profile, Errors errors,
                                Model model, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            model.addAttribute(account);
            return SETTINGS_PROFILE_VIEW_NAME;
        }

        accountService.updateProfile(account, profile);
        attributes.addFlashAttribute("message", "프로필을 수정했습니다.");
        return "redirect:" + SETTINGS_PROFILE_URL;
    }



    @GetMapping(SETTINGS_PASSWORD_URL)
    public String updatePasswordForm(@CurrentUser Account account, Model model) {

        model.addAttribute(account);
        model.addAttribute(new PasswordForm());



        return SETTINGS_PASSWORD_VIEW_NAME;

    }


    @PostMapping(SETTINGS_PASSWORD_URL)
    public String updatePassword(@CurrentUser Account account,
                                 @Valid PasswordForm passwordForm, Errors errors,
                                 Model model, RedirectAttributes attributes) {


        if (errors.hasErrors()) {
            model.addAttribute(account);
            return SETTINGS_PASSWORD_VIEW_NAME;
        }

        accountService.updatePassword(account, passwordForm.getNewPassword());
        attributes.addFlashAttribute("message", "패스워드를 변경했습니다.");
        return "redirect:" + SETTINGS_PASSWORD_URL;


    }

    @GetMapping(SETTINGS_NOTIFICATIONS_URL)
    public String updateNotificationsForm(@CurrentUser Account account, Model model) {

        model.addAttribute(account);
        model.addAttribute(modelMapper.map(account, Notifications.class));


        return SETTINGS_NOTIFICATIONS_VIEW_NAME;

    }


    @PostMapping(SETTINGS_NOTIFICATIONS_URL)
    public String updateNotifications(@CurrentUser Account account,
                                      @Valid Notifications notifications, Errors errors,
                                      Model model, RedirectAttributes attributes) {


        if (errors.hasErrors()) {
            model.addAttribute(account);
            return SETTINGS_NOTIFICATIONS_VIEW_NAME;
        }

        accountService.updateNotifications(account, notifications);
        attributes.addFlashAttribute("message", "알림 설정을 변경했습니다.");
        return "redirect:" + SETTINGS_NOTIFICATIONS_URL;


    }


    @GetMapping(SETTINGS_NICKNAME_URL)
    public String updateNicknameForm(@CurrentUser Account account, Model model) {

        model.addAttribute(account);
        model.addAttribute(modelMapper.map(account, NicknameForm.class));


        return SETTINGS_NICKNAME_VIEW_NAME;

    }


    @PostMapping(SETTINGS_NICKNAME_URL)
    public String updateNicknameForm(@CurrentUser Account account,
                                     @Valid NicknameForm nickname, Errors errors,
                                     Model model, RedirectAttributes attributes) {


        if (errors.hasErrors()) {
            model.addAttribute(account);
            return SETTINGS_NICKNAME_VIEW_NAME;
        }

        accountService.updateNickname(account, nickname);
        attributes.addFlashAttribute("message", "알림 설정을 변경했습니다.");
        return "redirect:" + SETTINGS_NICKNAME_VIEW_NAME;


    }



}


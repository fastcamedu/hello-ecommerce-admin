package com.fastcampus.ecommerce.admin.controller;

import com.fastcampus.ecommerce.admin.domain.user.AdminUser;
import com.fastcampus.ecommerce.admin.service.AdminUserDetailService;
import com.fastcampus.ecommerce.admin.service.dto.AdminUserFormDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserDetailService adminUserDetailService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/users/register")
    public String adminUserForm(Model model) {
        log.info(">>> 회원 가입 폼");
        return "/users/register";
    }

    @PostMapping(value = "/users/register")
    public String create(AdminUserFormDTO adminUserFormDTO) {
        log.info(">>> 회원 가입 진행, {}", adminUserFormDTO);
        AdminUser newAdminUser = AdminUser.createAdminUser(adminUserFormDTO, passwordEncoder);
        AdminUser savedAdminuser = adminUserDetailService.save(newAdminUser);
        return "redirect:/";
    }
}

package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.util.MobileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Value("${company.phone:전화번호 없음}")
    private String companyPhone;

    @GetMapping("/user/{page}")
    public String userPage(@PathVariable String page, Model model, HttpServletRequest req) {
        model.addAttribute("companyPhone", companyPhone);

        if (MobileUtils.isMobile(req)) {
            // 모바일이면 m.도메인으로 리다이렉트
            String host = req.getServerName(); // 현재 도메인
            String mobileHost = host.startsWith("m.") ? host : "m." + host;
            String url = "https://" + mobileHost + "/user/" + page;
            return "redirect:" + url;
        }

        // PC이면 그냥 기존 URL
        return "user/" + page;
    }

    // --- 모바일 페이지(운영용) ---
    @GetMapping("/m/user/{page}")
    public String mobilePage(@PathVariable String page, Model model) {
        model.addAttribute("companyPhone", companyPhone);
        return "user/m/" + page;
    }

    // --- ☆ 모바일 테스트 페이지(개발용) ---
    @GetMapping("/mtest/{page}")
    public String mobileTest(@PathVariable String page, Model model) {
        model.addAttribute("companyPhone", companyPhone);
        return "user/m/" + page;
    }
}
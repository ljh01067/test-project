package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.util.MobileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterController {

    @Value("${company.phone:전화번호 없음}")
    private String companyPhone;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest req) {
        model.addAttribute("companyPhone", companyPhone);
        return MobileUtils.view("main", req); // PC/모바일 자동 분기
    }
}
package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterController {

    @Value("${company.phone:전화번호 없음}")
    private String companyPhone;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("companyPhone", companyPhone);
        return "user/main";
    }
}
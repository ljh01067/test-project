package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Value("${company.phone:전화번호 없음}")
    private String companyPhone;
    @GetMapping("/user/main")
    public String mainPage(Model model) {
        model.addAttribute("companyPhone", companyPhone);
        return "user/main";
    }
    @GetMapping("/user/request")
    public String requestPage(Model model) {
        model.addAttribute("companyPhone", companyPhone);
        return "user/request";
    }
    @GetMapping("/user/works")
    public String worksPage(Model model) {
        model.addAttribute("companyPhone", companyPhone);
        return "user/works";
    }
    @GetMapping("/user/artists")
    public String artistsPage(Model model) {
        model.addAttribute("companyPhone", companyPhone);
        return "user/artists";
    }
    @GetMapping("/user/contact")
    public String contactPage(Model model) {
        model.addAttribute("companyPhone", companyPhone);
        return "user/contact";
    }
}
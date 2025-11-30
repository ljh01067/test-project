package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/main")
    public String mainPage() {
        return "user/main";
    }
    @GetMapping("/user/request")
    public String requestPage() {
        return "user/request";
    }
    @GetMapping("/user/works")
    public String worksPage() {
        return "user/works";
    }
    @GetMapping("/user/artists")
    public String artistsPage() {
        return "user/artists";
    }
    @GetMapping("/user/contact")
    public String contact4Page() {
        return "user/contact";
    }
}